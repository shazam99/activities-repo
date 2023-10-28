package com.npci.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.npci.exceptions.EmployeeNotFoundException;
import com.npci.services.EmployeeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService service;

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody Map<String, String> map) {
		
		try {
			return ResponseEntity.status(200).body(service.login(map));
		} catch (EmployeeNotFoundException e) {
			System.err.println(e.getMessage());
			Map<String, String> errors = new HashMap<>();
			errors.put("error", e.getMessage());
			return ResponseEntity.status(404).body(errors);
		}
	}

}
