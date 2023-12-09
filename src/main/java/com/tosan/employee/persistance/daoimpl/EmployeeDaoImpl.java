package com.tosan.employee.persistance.daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tosan.employee.persistance.dao.EmployeeDao;
import com.tosan.employee.persistance.entity.DepartmentAndAverageSalary;
import com.tosan.employee.persistance.entity.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	public List<DepartmentAndAverageSalary> averageSalaryByDepartment() {
		List<DepartmentAndAverageSalary> list = new ArrayList<>();
		ResultSet resultSet;
		String sql = "SELECT department, AVG(salary) AS avg FROM employees GROUP BY department";
		try {
			resultSet = JdbcConnection.getJdbcConnection().statement.executeQuery(sql);
			while (resultSet.next()) {
				list.add(new DepartmentAndAverageSalary(resultSet.getString("department"), resultSet.getDouble("avg")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	public List<Employee> maxSalaryByDepartment() {
		List<Employee> list = new ArrayList<>();
		ResultSet resultSet;
		String sql = "SELECT emp.* FROM company.employees emp JOIN (SELECT department, MAX(salary) AS max FROM company.employees GROUP BY department) groupby ON (emp.department = groupby.department AND emp.salary = groupby.max) ";
		try {
			resultSet = JdbcConnection.getJdbcConnection().statement.executeQuery(sql);
			while (resultSet.next()) {
				list.add(new Employee(resultSet.getLong("id"), resultSet.getString("first_name"),
						resultSet.getString("last_name"), resultSet.getDouble("salary"),
						resultSet.getString("department")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	public List<Employee> salaryLessThanAverage() {
		List<Employee> list = new ArrayList<>();
		ResultSet resultSet;
		String sql = "SELECT emp.* FROM company.employees emp WHERE emp.salary <= (SELECT  Avg(salary) AS avr FROM company.employees)";
		try {
			resultSet = JdbcConnection.getJdbcConnection().statement.executeQuery(sql);
			while (resultSet.next()) {
				list.add(new Employee(resultSet.getLong("id"), resultSet.getString("first_name"),
						resultSet.getString("last_name"), resultSet.getDouble("salary"),
						resultSet.getString("department")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	@Override
	public void save(List<Employee> list) {
		for (Employee item : list) {
			String sql = "INSERT INTO employees (first_name, last_name, salary, department) VALUES ('"
					+ item.getFirstName() + "', '" + item.getLastName() + "', '" + item.getSalary() + "', '"
					+ item.getDepartment() + "');";
			System.out.println(sql);
			try {
				JdbcConnection.getJdbcConnection().statement.executeUpdate(sql);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public void drop() {
		String sql = "TRUNCATE employees;";
		System.out.println(sql);
		try {
			JdbcConnection.getJdbcConnection().statement.executeUpdate(sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateSalary(Long id, Double salary) {
		String sql = "UPDATE company.employees SET salary = " + salary + " WHERE id = " + id + " ";
		Connection connection = JdbcConnectionPool.getConnection();
		try {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			connection.createStatement().executeUpdate(sql);
			System.out.println(sql);
			Thread.sleep(5000);
			connection.rollback();
			System.out.println("Update transaction is rollbacked.");
			JdbcConnectionPool.releaseConnection(connection);
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
			throw new RuntimeException(e);
		}

	}

	@Override
	public Employee getEmployeeById(Long id) {
		ResultSet resultSet;
		Employee employee = null;
		String sql = "SELECT * FROM company.employees  WHERE id = " + id;
		try {
			Connection connection = JdbcConnectionPool.getConnection();
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			Thread.sleep(1);
			resultSet = connection.createStatement().executeQuery(sql);
			System.out.println(sql);
			while (resultSet.next()) {
				employee = new Employee(resultSet.getLong("id"), resultSet.getString("first_name"),
						resultSet.getString("last_name"), resultSet.getDouble("salary"),
						resultSet.getString("department"));
			}
			JdbcConnectionPool.releaseConnection(connection);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return employee;
	}
}