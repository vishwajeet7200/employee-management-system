package com.management.employee.service;

import java.util.List;

import com.management.employee.model.EmployeeModel;

public interface EmployeeService {

	EmployeeModel saveEmployee(EmployeeModel employee);

	List<EmployeeModel> allEmployees();

	boolean deleteEmployee(long id);

	EmployeeModel editEmployee(long id,EmployeeModel employeeModel);

	EmployeeModel getEmpById(long id);

	

}
