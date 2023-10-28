package com.npci.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.npci.entity.Customer;
import com.npci.entity.LoanApplication;
import com.npci.exceptions.CustomerNotFoundException;
import com.npci.exceptions.ValidationException;
import com.npci.services.CustomerService;
import com.npci.services.LoanApplicationService;

@CrossOrigin("*")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;
    
    @Autowired
    private LoanApplicationService loanservice;
    

    @PostMapping("/register")
    public ResponseEntity<Object> registerCustomer(@RequestBody Customer customer) throws ValidationException, CustomerNotFoundException {
    	try {
    		return ResponseEntity.status(200).body(service.registerCustomer(customer));
    	} catch (ValidationException e) {
			System.err.println(e.getMessage());
			Map<String, String> errors = new HashMap<>();
			errors.put("error", e.getMessage());
			return ResponseEntity.status(404).body(errors);
		}
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginCustomer(@RequestBody Map<String, String> credentials) throws CustomerNotFoundException {
        try {
        	return ResponseEntity.status(200).body(service.loginCustomer(credentials));        	
        } catch (CustomerNotFoundException e) {
			System.err.println(e.getMessage());
			Map<String, String> errors = new HashMap<>();
			errors.put("error", e.getMessage());
			return ResponseEntity.status(404).body(errors);
		}
    }

    @PostMapping("/apply-loan")
	public ResponseEntity<Object> applyLoan(@RequestBody Map<String, String> loanDetails) throws CustomerNotFoundException{
		return ResponseEntity.status(200).body(loanservice.applyLoan(loanDetails));
	}
    
    @GetMapping("/getloans/{id}")
    public ResponseEntity<Object> getLoan(@PathVariable("id") int id) throws CustomerNotFoundException{
		return ResponseEntity.status(200).body(loanservice.getLoan(id));
	}

}
