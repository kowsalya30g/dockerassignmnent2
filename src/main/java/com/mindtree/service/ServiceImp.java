package com.mindtree.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.entity.EmployeeDetails;
import com.mindtree.exception.EmployeeServiceException;
import com.mindtree.repository.EmployeeRepo;

@Service
public class ServiceImp {
	@Autowired
	public EmployeeRepo empR;

	public EmployeeDetails addEmployeeSer(EmployeeDetails employee) throws EmployeeServiceException {
		
		int id=employee.getId();
		EmployeeDetails emp=null;
		emp=empR.findById(id);
		if(emp==null) {
		empR.save(employee);
		}else {
			throw new EmployeeServiceException("Id already present");
		}
		return employee;
	}

	public EmployeeDetails getById(int id) throws EmployeeServiceException {
		EmployeeDetails emp=null;
		emp=empR.findById(id);
		if(emp==null)
			throw new EmployeeServiceException("no id like that");
		
		return emp;
	}

	public List<EmployeeDetails> getAll() throws EmployeeServiceException {
		List<EmployeeDetails> list=new ArrayList<EmployeeDetails>();
		empR.findAll().forEach(list::add);
		if(list==null)
			throw new EmployeeServiceException("not any data");
		
		return list;
	}

	public List<EmployeeDetails> delete(int id) throws EmployeeServiceException {
		List<EmployeeDetails> list=new ArrayList<EmployeeDetails>();
		EmployeeDetails emp=null;
		emp=empR.findById(id);
		if(emp==null)
			throw new EmployeeServiceException("No Id like that");
		empR.deleteById(id);
		empR.findAll().forEach(list::add);
		return list;
		
	}
   /* public EmployeeDetails updateEmployee(EmployeeDetails employee,int id) throws EmployeeServiceException {
	
		EmployeeDetails emp=null;
		emp=empR.setId(id);
		empR.save(emp);
		return emp;	
    }*/



}
