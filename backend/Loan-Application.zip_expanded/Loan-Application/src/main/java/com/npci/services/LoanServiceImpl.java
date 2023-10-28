package com.npci.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npci.dao.LoanDao;
import com.npci.entity.Loan;

@Service
public class LoanServiceImpl implements LoanService{
	
	@Autowired
	LoanDao loan;

	@Override
	public List<Loan> getAll() {
		
		return loan.findAll();
	}

}
