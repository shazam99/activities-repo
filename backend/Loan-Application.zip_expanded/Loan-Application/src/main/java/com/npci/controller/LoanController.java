package com.npci.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.npci.services.LoanService;

@CrossOrigin("*")
@RestController
@RequestMapping("/loan")
public class LoanController {
	
	@Autowired
	LoanService service;
	
	@GetMapping("/fetchAll")
	public ResponseEntity<Object> fetchall(){
		return ResponseEntity.status(200).body(service.getAll());
	}
}
