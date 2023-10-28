package com.npci.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npci.dao.CustomerDao;
import com.npci.dao.LoginLogsDao;
import com.npci.entity.Customer;
import com.npci.entity.LoginLogs;
import com.npci.exceptions.CustomerNotFoundException;
import com.npci.exceptions.ValidationException;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao customerdao;
	
	@Autowired
	LoginLogsDao loginlogs;

	@Override
	public Customer registerCustomer(Customer customer) throws ValidationException, CustomerNotFoundException {
			if (verify(customer)) {
				customerdao.save(customer);			
				return customerdao.findById(customer.getCustomerId()).get();
			} else {
				throw new CustomerNotFoundException("Customer not found");
			}
	}

	
	@Override
	public Customer loginCustomer(Map<String, String> credentials) throws CustomerNotFoundException {
	    if (credentials.isEmpty()) {
	        throw new CustomerNotFoundException("Credentials are Empty");
	    }

	    String email = credentials.get("emailId");
	    String password = credentials.get("password");

	    Customer cust = customerdao.findByEmail(email);

	    if (cust != null) {
	        if (cust.getPassword().equals(password)) {
	        	loginlogs.save(new LoginLogs(0,cust.getEmailId(),"Login Success",cust.getClass().getSimpleName()));
	            return cust;
	        } else {
	        	loginlogs.save(new LoginLogs(0,cust.getEmailId(),"Login Failed",cust.getClass().getSimpleName()));
	            throw new CustomerNotFoundException("Invalid password");
	        }
	    } else {
	        throw new CustomerNotFoundException("Customer not found");
	    }
	}


	public boolean verify(Customer p) throws ValidationException {

		if (!checkEmail(p.getEmailId())) {
			throw new ValidationException("Invalid Email!");
		}
		if (p.getFirstName().length() < 3) {
			throw new ValidationException("first name contains minimum 3 characters!");
		}
		if (p.getLastName().length() < 1) {
			throw new ValidationException("Last name contains minimum 1 characters!");
		}
		if (!checkPassword(p.getPassword())) {
			throw new ValidationException("Invalid Password!");
		}
		if (p.getPan() == 0) {
			throw new ValidationException("Pan Required!");
		}
		if (Long.toString(p.getPhone()).length() != 10) {
			throw new ValidationException("Phone no should be 10 digits only!");
		}
		return true;
	}

	public boolean checkEmail(String email) {
		if (email.contains("@") && email.contains(".")) {
			return true;
		}
		return false;
	}

	public boolean checkPassword(String password) {

		boolean len = false; // length
		boolean digit = false; // 1 digit
		boolean upper = false; // 1 upper
		boolean lower = false; // 1 lower
		boolean specialChar = false; // 1 special

		if (password.length() >= 6) {
			len = true;
		}

		for (int i = 0; i < password.length(); i++) {
			char ch = password.charAt(i);
			if (Character.isDigit(ch)) {
				digit = true;
			} else if (Character.isUpperCase(ch)) {
				upper = true;
			} else if (Character.isLowerCase(ch)) {
				lower = true;
			} else if (!Character.isLetterOrDigit(ch)) {
				specialChar = true;
			}
		}

		return (len && digit && upper && lower && specialChar);
	}

}
