package com.management.employee.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.employee.entity.EmployeeEntity;
import com.management.employee.model.EmployeeModel;
import com.management.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public EmployeeModel saveEmployee(EmployeeModel employee) {
		EmployeeEntity employeeEntity=new EmployeeEntity();
		BeanUtils.copyProperties(employee, employeeEntity);
		System.out.println(employeeEntity);
		employeeRepository.save(employeeEntity);
		return employee;
	}

	@Override
	public List<EmployeeModel> allEmployees() {
		
		List<EmployeeEntity> employeeEntities= employeeRepository.findAll();
		List<EmployeeModel> employeeModels= employeeEntities
										   .stream()
										   .map(emp-> new EmployeeModel(
												   emp.getId(),
												   emp.getFirstName(),
												   emp.getLastName(),
												   emp.getEmailId()
												   ))
										   .collect(Collectors.toList());
		return employeeModels;
	}

	@Override
	public boolean deleteEmployee(long id) {
		// TODO Auto-generated method stub
		//Optional<EmployeeEntity> employee=employeeRepository.findById(id);
		employeeRepository.deleteById(id);
		return true;
	}

	@Override
	public EmployeeModel editEmployee(long id,EmployeeModel employeeModel) {
		// TODO Auto-generated method stub
		
		EmployeeEntity employeeEntity=employeeRepository.findById(id).get();
		employeeEntity.setFirstName(employeeModel.getFirstName());
		employeeEntity.setLastName(employeeModel.getLastName());
		employeeEntity.setEmailId(employeeModel.getEmailId());
		employeeRepository.save(employeeEntity);
		return employeeModel;
	}

	@Override
	public EmployeeModel getEmpById(long id) {
		// TODO Auto-generated method stub
		EmployeeEntity employeeEntity=employeeRepository.findById(id).get();
		EmployeeModel employeeModel=new EmployeeModel();
		BeanUtils.copyProperties(employeeEntity, employeeModel);
		return employeeModel;
	}
	
}
