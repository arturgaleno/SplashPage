package br.com.artur.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TransposedSurveyAnswer {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;
	
	@Column
	private BigInteger expected1;
	
	@Column
	private BigInteger pay1;
	
	@Column
	private BigInteger needs1;
	
	@Column
	private BigInteger expected2;
	
	@Column
	private BigInteger pay2;
	
	@Column
	private BigInteger needs2;
	
	@Column
	private BigInteger expected3;
	
	@Column
	private BigInteger pay3;
	
	@Column
	private BigInteger needs3;

	public BigInteger getExpected1() {
		return expected1;
	}

	public void setExpected1(BigInteger expected1) {
		this.expected1 = expected1;
	}

	public BigInteger getPay1() {
		return pay1;
	}

	public void setPay1(BigInteger pay1) {
		this.pay1 = pay1;
	}

	public BigInteger getNeeds1() {
		return needs1;
	}

	public void setNeeds1(BigInteger needs1) {
		this.needs1 = needs1;
	}

	public BigInteger getExpected2() {
		return expected2;
	}

	public void setExpected2(BigInteger expected2) {
		this.expected2 = expected2;
	}

	public BigInteger getPay2() {
		return pay2;
	}

	public void setPay2(BigInteger pay2) {
		this.pay2 = pay2;
	}

	public BigInteger getNeeds2() {
		return needs2;
	}

	public void setNeeds2(BigInteger needs2) {
		this.needs2 = needs2;
	}

	public BigInteger getExpected3() {
		return expected3;
	}

	public void setExpected3(BigInteger expected3) {
		this.expected3 = expected3;
	}

	public BigInteger getPay3() {
		return pay3;
	}

	public void setPay3(BigInteger pay3) {
		this.pay3 = pay3;
	}

	public BigInteger getNeeds3() {
		return needs3;
	}

	public void setNeeds3(BigInteger needs3) {
		this.needs3 = needs3;
	}
}
