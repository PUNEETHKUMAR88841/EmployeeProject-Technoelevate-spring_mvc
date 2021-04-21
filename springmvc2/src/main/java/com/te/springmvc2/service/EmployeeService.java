package com.te.springmvc2.service;

import com.te.springmvc2.bean.EmployeeBean;

public interface EmployeeService {
	
public EmployeeBean authenticate(int id, String password);
	
	public EmployeeBean searchemp(int id);
	
	public boolean deleteEmp(int id);
	
	public java.util.List<EmployeeBean> getAllData();
	
	public boolean addemp(EmployeeBean employeeBean);
	
	public boolean update(EmployeeBean bean);

}
