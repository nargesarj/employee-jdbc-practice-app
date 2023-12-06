package com.tosan.employee.presentation;

import java.util.List;

import com.tosan.employee.service.EmployeeServiceApp;

public class MyEmployeeApp {

	public static void main(String[] args) {

		init();

		System.out.println("");
		System.out.println("--------------------------------------------------------------");
		System.out.println("getAverageEmployeeSalaryInEachDepartment");
		EmployeeServiceApp employeeServiceApp = new EmployeeServiceApp();
		List<String> EmployeeInfoList = employeeServiceApp.getEmployeeAverageSalary();
		for (String string : EmployeeInfoList)
			System.out.println(string);

		System.out.println("");
		System.out.println("--------------------------------------------------------------");
		System.out.println("getEmployeeInfoHowHasMaxSalaryInEachDepartment");
		employeeServiceApp = new EmployeeServiceApp();
		EmployeeInfoList = employeeServiceApp.getMaxSalaryByDepartment();
		for (String string : EmployeeInfoList)
			System.out.println(string);

		System.out.println("");
		System.out.println("--------------------------------------------------------------");
		System.out.println("getEmployeeInfoHowHasLessSalaryThanAvarege");
		employeeServiceApp = new EmployeeServiceApp();
		EmployeeInfoList = employeeServiceApp.getSalaryLessThanAverage();
		for (String string : EmployeeInfoList)
			System.out.println(string);

	}

	public static void init() {
		EmployeeServiceApp employeeServiceApp = new EmployeeServiceApp();
		employeeServiceApp.dropAllEmployees();
		employeeServiceApp.insertDefaultEmployeeList();
	}
}
