package com.spring.boot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "user_education")
public class UserEducation extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "qualification")
	private String qualification;
	
	@Column(name = "passed_year")
	private int passedYear;
	
	@Column(name = "certification")
	private String certification;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="mt_user_id",referencedColumnName="id")
	private MTUser mtUser;
	
	
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public int getPassedYear() {
		return passedYear;
	}
	public void setPassedYear(int passedYear) {
		this.passedYear = passedYear;
	}
	public String getCertification() {
		return certification;
	}
	public void setCertification(String certification) {
		this.certification = certification;
	}
	public MTUser getMtUser() {
		return mtUser;
	}
	public void setMtUser(MTUser mtUser) {
		this.mtUser = mtUser;
	}
	
	
	

}
