package com.npci.services;

import java.util.Map;

import com.npci.entity.Customer;
import com.npci.exceptions.CustomerNotFoundException;
import com.npci.exceptions.ValidationException;

public interface CustomerService {

	public Customer registerCustomer(Customer customer) throws ValidationException, CustomerNotFoundException;

	public Customer loginCustomer(Map<String, String> credentials) throws CustomerNotFoundException;

}
