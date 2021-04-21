package com.te.springmvc2.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;

import com.te.springmvc2.bean.EmployeeBean;
import com.te.springmvc2.customexception.EmployeeExp;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	@Override
	public EmployeeBean authenticate(int id, String password) {

//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("empl");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {
			EmployeeBean bean = entityManager.find(EmployeeBean.class, id);
			if (bean != null) {
				if (bean.getPassword().equalsIgnoreCase(password)) {
					System.out.println("login successfull");
					return bean;
				} else {
					System.out.println("invalid credentials");
					throw new EmployeeExp("user not found");
				}
			} else {
				throw new EmployeeExp("user not found");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new EmployeeExp("user not found");
		}

	}//end of authenticate

	@Override
	public EmployeeBean searchemp(int id) {
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("empl");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		EmployeeBean bean = entityManager.find(EmployeeBean.class, id);
		if (bean != null) {
			return bean;
		} else {
			return null;
		}

	}//end of search

	@Override
	public boolean deleteEmp(int id) {
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("empl");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		entityTransaction.begin();
		EmployeeBean employeeBean = entityManager.find(EmployeeBean.class, id);
		if (employeeBean != null) {
			entityManager.remove(employeeBean);
			entityTransaction.commit();
		} else {
			return false;
		}
		return false;

	}//end of deleteemp

	@Override
	public List<EmployeeBean> getAllData() {
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("empl");

		EntityManager manager = entityManagerFactory.createEntityManager();

		String query = "from EmployeeBean";

		javax.persistence.Query query2 = manager.createQuery(query);

		List<EmployeeBean> list = query2.getResultList();
		if (list != null) {
			return list;
		} else {
			return null;
		}
	}//end of getalldata

	@Override
	public boolean addemp(EmployeeBean employeeBean) {
		
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("empl");

		EntityManager manager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = manager.getTransaction();
		boolean isAdd = false;
		try {
			entityTransaction.begin();
			manager.persist(employeeBean);
			if(employeeBean != null) {
				entityTransaction.commit();
				isAdd=true;
			}
		} catch (Exception e) {
			isAdd = false;
			e.printStackTrace();
		}
		return isAdd;
	}//end of addemp

	@Override
	public boolean update(EmployeeBean bean) {
		EntityManager manager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = manager.getTransaction();
		boolean isUpdate = false;
		
		try {
			entityTransaction.begin();
			EmployeeBean info = manager.find(EmployeeBean.class, bean.getId());
			if(bean.getName()!=null && bean.getName()!="") {
				info.setName(bean.getName());
			}
			if(bean.getDob()!=null) {
				info.setDob(bean.getDob());
			}
			if(bean.getPassword()!=null && bean.getPassword()!="") {
				info.setPassword(bean.getPassword());
			}
			entityTransaction.commit();
			isUpdate=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			isUpdate=false;
			e.printStackTrace();
		}
		
		return isUpdate;
		
	}//end of update

	

}//end of class
