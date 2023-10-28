package com.npci.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.npci.entity.Loan;

public interface LoanDao extends JpaRepository<Loan, Integer>{

}
