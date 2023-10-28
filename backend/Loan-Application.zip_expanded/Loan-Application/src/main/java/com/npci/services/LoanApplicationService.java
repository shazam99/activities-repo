package com.npci.services;

import java.util.List;
import java.util.Map;

import com.npci.entity.LoanApplication;
import com.npci.exceptions.CustomerNotFoundException;
import com.npci.exceptions.EmployeeNotFoundException;

public interface LoanApplicationService {
	
	public LoanApplication applyLoan(Map<String, String> loanDetails) throws CustomerNotFoundException;

	public List<LoanApplication> getLoan(int id);

}
