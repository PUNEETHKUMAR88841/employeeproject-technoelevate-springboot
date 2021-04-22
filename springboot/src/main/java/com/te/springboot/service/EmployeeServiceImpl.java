package com.te.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.springboot.dao.EmployeeDAO;
import com.te.springboot.dto.EmployeeBean;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDAO dao;
	

	@Override
	public EmployeeBean searchemp(int id) {
		
		return dao.searchemp(id);
	}

	@Override
	public boolean deleteEmp(int id) {
	
		return dao.deleteEmp(id);
	}

	@Override
	public List<EmployeeBean> getAllData() {
	
		return dao.getAllData();
	}

	@Override
	public boolean addemp(EmployeeBean employeeBean) {
		
		return dao.addemp(employeeBean);
	}

	@Override
	public boolean update(EmployeeBean bean) {
		
		return dao.update(bean);
	}
}

