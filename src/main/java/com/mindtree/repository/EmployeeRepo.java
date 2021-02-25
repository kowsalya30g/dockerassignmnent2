package com.mindtree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.entity.EmployeeDetails;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeDetails,Integer>{
	public EmployeeDetails findById(int id);

}
