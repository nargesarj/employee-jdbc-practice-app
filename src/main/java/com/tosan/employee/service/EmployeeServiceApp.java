package com.tosan.employee.service;

import java.util.ArrayList;
import java.util.List;

import com.tosan.employee.persistance.dao.EmployeeDao;
import com.tosan.employee.persistance.daoimpl.EmployeeDaoImpl;
import com.tosan.employee.persistance.entity.DepartmentAndAverageSalary;
import com.tosan.employee.persistance.entity.Employee;

public class EmployeeServiceApp {

	private EmployeeDao emlDao = new EmployeeDaoImpl();

	public List<String> getEmployeeAverageSalary() {

		List<DepartmentAndAverageSalary> list = emlDao.averageSalaryByDepartment();
		List<String> strList = new ArrayList<>();
		for (DepartmentAndAverageSalary temp : list) {
			strList.add(temp.toString());
		}
		return strList;
	}

	public List<String> getMaxSalaryByDepartment() {

		List<Employee> list = emlDao.maxSalaryByDepartment();
		List<String> strList = new ArrayList<>();
		for (Employee employee : list) {
			strList.add(employee.toString());
		}
		return strList;
	}

	public List<String> getSalaryLessThanAverage() {

		List<Employee> list = emlDao.salaryLessThanAverage();
		List<String> strList = new ArrayList<>();
		for (Employee employee : list) {
			strList.add(employee.toString());
		}
		return strList;
	}
	
	public void insertDefaultEmployeeList() {
		List<Employee> list = new ArrayList<>();
		list.add(new Employee("John", "Johni", 1213454D, "1"));
		list.add(new Employee("Faz", "Johni", 5111777D, "2"));
		list.add(new Employee("Nol", "Johni", 123000D, "2"));
		list.add(new Employee("Jack", "Johni", 999000D, "4"));
		list.add(new Employee("Sara", "Johni", 123000D, "5"));
		list.add(new Employee("My", "Johni", 4444444D, "2"));
		list.add(new Employee("Why", "Johni", 123000D, "7"));
		list.add(new Employee("Edvrd", "Johni", 4667765D, "9"));
		list.add(new Employee("John", "Johni", 123000D, "9"));
		list.add(new Employee("July", "Johni", 1230440D, "10"));
		
		emlDao.save(list);
	}
	
	public void dropAllEmployees() {
		emlDao.drop();
	}
}
