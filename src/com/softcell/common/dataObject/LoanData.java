package com.softcell.common.dataObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="softcell_loan_data")
public class LoanData {

	public LoanData(){
		
	}
	
	@Id
	@Column (name="loan_id")
	public int loan_id;
	
	@Column (name="activity_id")
	public int activity_id;
	
	@Column (name="amount")
	public int amount;
	
	@Column (name="tenure")
	public int tenure;
	
	@Column (name="customer_income")
	public int customer_income;
	
	@Column (name="marital_status")
	public String marital_status;
	
	@Column (name="Gender")
	public String Gender;
	
	@Column (name="age")
	public int age;
	
	@Column (name="race")
	public String race;
	
	@Column (name="education")
	public String education;
	
	@Column (name="employment_status")
	public String employment_status;

	public int getLoan_id() {
		return loan_id;
	}

	public void setLoan_id(int loan_id) {
		this.loan_id = loan_id;
	}

	public int getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(int activity_id) {
		this.activity_id = activity_id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	public int getCustomer_income() {
		return customer_income;
	}

	public void setCustomer_income(int customer_income) {
		this.customer_income = customer_income;
	}

	public String getMarital_status() {
		return marital_status;
	}

	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getEmployment_status() {
		return employment_status;
	}

	public void setEmployment_status(String employment_status) {
		this.employment_status = employment_status;
	}
	
	
}

