package com.npci.services;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npci.dao.EmployeeDao;
import com.npci.dao.LoginLogsDao;
import com.npci.entity.Employee;
import com.npci.entity.LoginLogs;
import com.npci.exceptions.EmployeeNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeDao employee;
	
	@Autowired
	LoginLogsDao loginlogs;

	@Override
	public Employee login(Map<String, String> map) throws EmployeeNotFoundException {
	    if (map.isEmpty()) {
	        throw new EmployeeNotFoundException("Details are Empty");
	    }

	    int employee_id = Integer.parseInt(map.get("employee_id"));
	    String password = map.get("password");

	    Optional<Employee> optionalEmployee = employee.findById(employee_id);

	    if (optionalEmployee.isPresent()) {
	        Employee emp = optionalEmployee.get();

	        if (emp.getPassword().equals(password)) {
	        	loginlogs.save(new LoginLogs(0,emp.getEmail_id(),"Login Success",emp.getClass().getSimpleName()));
	            return emp;
	        } else {
	        	loginlogs.save(new LoginLogs(0,emp.getEmail_id(),"Login Failed",emp.getClass().getSimpleName()));
	            throw new EmployeeNotFoundException("Invalid password");
	        }
	    } else {
	        throw new EmployeeNotFoundException("Employee not found");
	    }
	}

		
		
	}
