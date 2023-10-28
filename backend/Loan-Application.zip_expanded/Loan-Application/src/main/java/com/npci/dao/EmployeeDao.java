package com.npci.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.npci.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer>{
}
