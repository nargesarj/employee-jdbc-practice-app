package com.tosan.employee.persistance.dao;

import java.util.List;

import com.tosan.employee.persistance.entity.DepartmentAndAverageSalary;
import com.tosan.employee.persistance.entity.Employee;

public interface EmployeeDao {

	List<DepartmentAndAverageSalary>  averageSalaryByDepartment();
	
	List<Employee> maxSalaryByDepartment();
	
	List<Employee> salaryLessThanAverage();
	
	void save(List<Employee> employee);
	
	void drop();
}
