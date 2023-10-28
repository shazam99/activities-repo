package com.npci.services;

import java.util.Map;

import com.npci.entity.Employee;
import com.npci.exceptions.EmployeeNotFoundException;

public interface EmployeeService {

	public Employee login(Map<String, String> map) throws EmployeeNotFoundException;
}
