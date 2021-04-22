package com.te.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.te.springboot.dto.EmployeeBean;
import com.te.springboot.dto.EmployeeResponse;
import com.te.springboot.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@GetMapping(path  ="/getEmp",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public EmployeeResponse getEmp(int id) {
		EmployeeResponse response=new EmployeeResponse();
		EmployeeBean bean=service.searchemp(id);
		if(bean!=null) {
			response.setStatusCode(200);
			response.setMsg("Success :)");
			response.setDescription("Employee Data found for id :"+id);
			response.setBean(bean);
			
		}else {
			response.setStatusCode(404);
			response.setMsg("Failue :(");
			response.setDescription("Employee data not found for id :"+id);
		}
		return response;
		
	}// end of getemp
	
	
	@GetMapping(path="/getAll",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public EmployeeResponse getAllEmp() {
		EmployeeResponse response=new EmployeeResponse();
		List<EmployeeBean> beans=service.getAllData();
		if (beans!=null) {
			response.setStatusCode(200);
			response.setMsg("Success :)");
			response.setDescription("Employee Details :");
			response.setEmployeeBeans(beans);
			
		}else {
			response.setStatusCode(404);
			response.setMsg("Failure :( ");
			response.setDescription("Data not found");
		}
		return response;
		
	}//end of GetAll

	@DeleteMapping(path ="/delete",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public EmployeeResponse deleteEmp(int id) {
		EmployeeResponse response=new EmployeeResponse();
	
		if(service.deleteEmp(id)) {
			response.setStatusCode(200);
			response.setMsg("Success");
			response.setDescription("Data deleted successfully for id :"+id);
			
		}else {
			response.setStatusCode(404);
			response.setMsg("Failure");
			response.setDescription("Data Not found to delete");
		}
		return response;
		
	}//end of delete

	@PostMapping(path = "/add", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public EmployeeResponse addEmp(@RequestBody EmployeeBean employeeBean) {
		EmployeeResponse response = new EmployeeResponse();
		if (service.addemp(employeeBean)) {
			response.setStatusCode(200);
			response.setMsg("success");
			response.setDescription("Added Successfully");
		} else {
			response.setStatusCode(400);
			response.setMsg("failure");
			response.setDescription("Something Went Wrong !");
		}
		return response;
	}// end of ADD
	
	@PutMapping(path = "/update", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public EmployeeResponse updateEmp(@RequestBody EmployeeBean bean) {
		System.out.println(bean);
		EmployeeResponse response = new EmployeeResponse();
		if (service.update(bean)) {
			response.setStatusCode(200);
			response.setMsg("success");
			response.setDescription("Updated Successfully");
		} else {
			response.setStatusCode(400);
			response.setMsg("failure");
			response.setDescription("Something Went Wrong");
		}
		return response;
	}// end of Update

		
	}//end of class
