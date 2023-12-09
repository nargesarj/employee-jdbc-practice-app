package com.tosan.employee.presentation;

import java.util.List;
import java.util.Scanner;

import com.tosan.employee.service.EmployeeServiceApp;

public class MyEmployeeApp {

	public static void main(String[] args) throws InterruptedException {

		Scanner scanner = new Scanner(System.in);

		while (true) {


			System.out.println("--------------------------------------------------------------------");
			System.out.println("");
			System.out.println("Please enter your number by following menu: ");
			System.out.println("*********************************");
			System.out.println("1. First JDBC practice result.");
			System.out.println("2. First ACID practice result.");
			System.out.println("0. Exit.");
			System.out.println("*********************************");
			System.out.println("");
			int userChoice = scanner.nextInt();

			switch (userChoice) {
			case 1: {
				doFirstPractice();
				break;
			}
			case 2: {
				doSecondPractice();
				Thread.sleep(11000);
				break;
			}
			case 0: {
				scanner.close();
				System.exit(0);
				break;
			}
			default:
				System.out.println("Unexpected value: " + userChoice);
			}
		}
	}

	private static void doSecondPractice() throws InterruptedException {
		EmployeeServiceApp employeeServiceApp = new EmployeeServiceApp();
		employeeServiceApp.testTransactionReadUncommited();
	}

	public static void init() {
		EmployeeServiceApp employeeServiceApp = new EmployeeServiceApp();
		employeeServiceApp.dropAllEmployees();
		employeeServiceApp.insertDefaultEmployeeList();
	}

	private static void doFirstPractice() {
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
		
		employeeServiceApp.closeConnection();
	}
}
