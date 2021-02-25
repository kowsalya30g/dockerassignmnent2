package com.mindtree.controller;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.mindtree.entity.EmployeeDetails;
import com.mindtree.exception.EmployeeServiceException;
import com.mindtree.service.ServiceImp;

@RestController
public class Employee {
	@Autowired
	public ServiceImp empSer;

	@GetMapping("/get/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") int id) {
		Map<String, Object> map = new LinkedHashMap<>();
		try {
			EmployeeDetails emp = empSer.getById(id);
			map.put("Employee found", emp);
			map.put("Message", "Successfully fetched from Employee");
			return new ResponseEntity<>(map,HttpStatus.OK);
		} catch (EmployeeServiceException e) {
			System.out.println("\n" + e.getLocalizedMessage());
			map.put("Message", "Failed to fetch the employee");
		}
		return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/addEmployee")
	public ResponseEntity<?> addEmployee(@RequestBody EmployeeDetails employee) {

		Map<String, Object> map = new LinkedHashMap<>();
		try {
			EmployeeDetails emp = empSer.addEmployeeSer(employee);
			map.put("Employee added", emp);
			map.put("Message", "Successfully added to Employee");
			return new ResponseEntity<>(map,HttpStatus.CREATED);

		} catch (EmployeeServiceException e) {
			System.out.println("\n" + e.getLocalizedMessage());
		    map.put("Message", "Id already exists");
		}
		return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAll() {

		Map<String, Object> map = new LinkedHashMap<>();
		try {
			List<EmployeeDetails> employee = empSer.getAll();
			map.put("Employee ", employee);
			map.put("Message", "Successfully displayed Employee");
			return new ResponseEntity<>(map,HttpStatus.OK);
		} catch (EmployeeServiceException e) {
			System.out.println("\n" + e.getLocalizedMessage());
			map.put("Message", "Failed to fetch the employee");
		}
		 return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") int id) {

		Map<String, Object> map = new LinkedHashMap<>();
		try {
			List<EmployeeDetails> employee = empSer.delete(id);
			return new ResponseEntity<>(map,HttpStatus.NO_CONTENT);
		} catch (EmployeeServiceException e) {
			System.out.println("\n" + e.getLocalizedMessage());
			map.put("Message", "Failed to fetch the employee");
		}
		return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
	}
	/*@PutMapping("/update/{id}")
	public Map<String, Object> updateById(@RequestBody EmployeeDetails employee, @PathVariable("id") int id) {

		Map<String, Object> map = new LinkedHashMap<>();
		try {
			EmployeeDetails emp = empSer.updateEmployee(employee,id);
			map.put("Status", "200 OK");
			map.put("Employee after updating", emp);
			map.put("Message", "Successfully updated into Employee");
		} catch (EmployeeServiceException e) {
			System.out.println("\n" + e.getLocalizedMessage());
			map.put("Status", "404 Not Found");
			map.put("Message", "Failed to fetch the employee");
		}
		return map;
	}*/

}
