package com.tosan.employee.persistance.entity;

public class DepartmentAndAverageSalary {

	private String department;
	private Double average;

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Double getAverage() {
		return average;
	}

	public void setAverage(Double average) {
		this.average = average;
	}

	public DepartmentAndAverageSalary(String department, Double average) {
		super();
		this.department = department;
		this.average = average;
	}

	@Override
	public String toString() {
		return "DepartmentAndAverageSalary [department=" + department + ", average=" + average + "]";
	}
	
}
