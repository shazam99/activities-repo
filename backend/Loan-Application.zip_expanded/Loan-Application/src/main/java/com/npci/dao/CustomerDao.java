package com.npci.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.npci.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer> {

	
	@Query("select c from Customer c where c.emailId=?1")
	public Customer findByEmail(String email);
}
