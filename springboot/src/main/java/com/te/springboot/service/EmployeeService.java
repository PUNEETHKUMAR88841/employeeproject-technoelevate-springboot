package com.te.springboot.service;

import com.te.springboot.dto.EmployeeBean;

public interface EmployeeService {
	public EmployeeBean searchemp(int id);
	
	public boolean deleteEmp(int id);
	
	public java.util.List<EmployeeBean> getAllData();
	
	public boolean addemp(EmployeeBean employeeBean);
	
	public boolean update(EmployeeBean bean);

}
