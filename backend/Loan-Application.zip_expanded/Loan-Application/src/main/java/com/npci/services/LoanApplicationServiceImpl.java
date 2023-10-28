package com.npci.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npci.dao.CustomerDao;
import com.npci.dao.LoanApplicationDao;
import com.npci.dao.LoanDao;
import com.npci.entity.Customer;
import com.npci.entity.Employee;
import com.npci.entity.Loan;
import com.npci.entity.LoanApplication;
import com.npci.entity.LoginLogs;
import com.npci.exceptions.CustomerNotFoundException;
import com.npci.exceptions.EmployeeNotFoundException;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService{
	
	@Autowired 
	CustomerDao customer;
	
	@Autowired
	LoanApplicationDao loanapplication;
	
	@Autowired
	LoanDao loan;
	

	@Override
	public LoanApplication applyLoan(Map<String, String> loanDetails) throws CustomerNotFoundException {

		if (loanDetails.isEmpty()) {
			throw new CustomerNotFoundException("Details are Empty");
		}
		int customerId = Integer.parseInt(loanDetails.get("customerId"));
		int loan_id = Integer.parseInt(loanDetails.get("loan_id"));
		
	    Optional<Customer> c = customer.findById(customerId);
	    Loan l = loan.findById(loan_id).get();
	    if (c.isPresent()) {
	        Customer cust = c.get();
	        LoanApplication la = new LoanApplication(0, cust, l, "Processing");
	        loanapplication.save(la);
	        return la;
	    } else {
	        throw new CustomerNotFoundException("Customer not found");
	    }
	}


	@Override
	public List<LoanApplication> getLoan(int id) {
		Customer c = customer.findById(id).get();
		return loanapplication.findAllByCustomerId(c);
	}
	
}
