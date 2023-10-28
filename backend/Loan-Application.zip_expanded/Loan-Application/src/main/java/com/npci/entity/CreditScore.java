package com.npci.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="credit_score_table")
public class CreditScore {
	
	@Id
	private int pan;
	
	private int score;

	public CreditScore() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreditScore(int pan, int score) {
		super();
		this.pan = pan;
		this.score = score;
	}

	public int getPan() {
		return pan;
	}

	public void setPan(int pan) {
		this.pan = pan;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	

}
