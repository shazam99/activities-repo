package com.npci.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.npci.entity.CreditScore;

public interface CreditScoreDao extends JpaRepository<CreditScore, Integer>{

}
