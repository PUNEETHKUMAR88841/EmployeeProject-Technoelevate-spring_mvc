package com.te.springmvc2.customexception;

public class EmployeeExp extends RuntimeException {

	public EmployeeExp(String msg) {
		super(msg);
	}

}
