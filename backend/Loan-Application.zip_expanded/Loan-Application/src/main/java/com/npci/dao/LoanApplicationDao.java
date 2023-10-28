package com.npci.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.npci.entity.Customer;
import com.npci.entity.LoanApplication;

public interface LoanApplicationDao extends JpaRepository<LoanApplication, Integer>{

	@Query("select la from LoanApplication la where la.customer = ?1")
	List<LoanApplication> findAllByCustomerId(Customer c);

}
