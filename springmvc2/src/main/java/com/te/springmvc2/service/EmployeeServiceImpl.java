package com.te.springmvc2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.springmvc2.bean.EmployeeBean;
import com.te.springmvc2.dao.EmployeeDAO;


@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeDAO dao;
	
	@Override
	public EmployeeBean authenticate(int id, String password) {
		// TODO Auto-generated method stub
		return dao.authenticate(id, password);
	}

	@Override
	public EmployeeBean searchemp(int id) {
		// TODO Auto-generated method stub
		return dao.searchemp(id);
	}

	@Override
	public boolean deleteEmp(int id) {
		// TODO Auto-generated method stub
		return dao.deleteEmp(id);
	}

	@Override
	public List<EmployeeBean> getAllData() {
		// TODO Auto-generated method stub
		return dao.getAllData();
	}

	@Override
	public boolean addemp(EmployeeBean employeeBean) {
		// TODO Auto-generated method stub
		return dao.addemp(employeeBean);
	}

	@Override
	public boolean update(EmployeeBean bean) {
		// TODO Auto-generated method stub
		return dao.update(bean);
	}

}
