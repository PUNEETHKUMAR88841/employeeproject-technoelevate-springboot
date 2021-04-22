package com.te.springboot.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;

import com.te.springboot.dto.EmployeeBean;

import ch.qos.logback.core.joran.conditional.IfAction;
import javassist.bytecode.stackmap.BasicBlock.Catch;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	EntityManager entityManager;

	@Override
	public EmployeeBean searchemp(int id) {

		entityManager = entityManagerFactory.createEntityManager();

		EmployeeBean bean = entityManager.find(EmployeeBean.class, id);
		if (bean != null) {
			return bean;
		} else {
			return null;
		}

	}// end of search

	public boolean deleteEmp(int id) {

		entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		try {
			entityTransaction.begin();
			EmployeeBean employeeBean = entityManager.find(EmployeeBean.class, id);
			if (employeeBean != null) {
				entityManager.remove(employeeBean);
				entityTransaction.commit();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}// end of deleteemp

	@Override
	public List<EmployeeBean> getAllData() {

		entityManager = entityManagerFactory.createEntityManager();

		String query = "from EmployeeBean";

		javax.persistence.Query query2 = entityManager.createQuery(query);

		List<EmployeeBean> list = query2.getResultList();
		if (list != null) {
			return list;
		} else {
			return null;
		}
	}// end of getalldata

	@Override
	public boolean addemp(EmployeeBean employeeBean) {

		entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		boolean isAdd = false;
		try {
			entityTransaction.begin();
			entityManager.persist(employeeBean);

			entityTransaction.commit();
			isAdd = true;

		} catch (Exception e) {
			entityTransaction.rollback();
			isAdd = false;
			e.printStackTrace();
		}
		return isAdd;
	}// end of addemp

	@Override
	public boolean update(EmployeeBean bean) {
		entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		boolean isUpdate = false;

		try {
			entityTransaction.begin();
			EmployeeBean info = entityManager.find(EmployeeBean.class, bean.getId());
			if (bean.getName() != null && bean.getName() != "") {
				info.setName(bean.getName());
			}
			if (bean.getDob() != null) {
				info.setDob(bean.getDob());
			}
			if (bean.getPassword() != null && bean.getPassword() != "") {
				info.setPassword(bean.getPassword());
			}
			entityTransaction.commit();
			isUpdate = true;
		} catch (Exception e) {

			e.printStackTrace();
		}

		return isUpdate;
	}
	// end of update


}//end of class

