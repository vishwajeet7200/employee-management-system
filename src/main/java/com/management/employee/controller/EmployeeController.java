package com.management.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.employee.entity.EmployeeEntity;
import com.management.employee.model.EmployeeModel;
import com.management.employee.service.EmployeeService;

import lombok.extern.java.Log;

@CrossOrigin(origins="http://localhost:3000/")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	@Autowired
	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService=employeeService;
	}
	
	@PostMapping("/createEmployee")
	public EmployeeModel createEmployee(@RequestBody EmployeeModel employee) {
		
		return employeeService.saveEmployee(employee);
	}
	
	@GetMapping("/allEmployee")
	public List<EmployeeModel> allEmployees(){
		
		List<EmployeeModel> employees = employeeService.allEmployees();
		
		return employees;
	}
	
	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable long id){
		
		boolean deleted=false;
		deleted=employeeService.deleteEmployee(id);
		Map<String,Boolean> response=new HashMap<>();
		response.put("Deleted", deleted);
		return ResponseEntity.ok(response);
		
	}
	
	@PutMapping("/editEmployee/{id}")
	public ResponseEntity<EmployeeModel> editEmployee(@PathVariable long id,@RequestBody EmployeeModel employeeModel) {
		return ResponseEntity.ok(employeeService.editEmployee(id,employeeModel));
		
	}
	
	@GetMapping("/getEmpById/{id}")
	public ResponseEntity<EmployeeModel> getEmployeeById(@PathVariable long id) {
		return ResponseEntity.ok(employeeService.getEmpById(id));
	}
	
	//test git
	
}
