package com.spring.boot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "user_employement")
public class UserEmployementDetails extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "designation")
	private String designation;
	
	@Column(name = "d_o_j")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateOfJoining;
	
	@Column(name = "years_of_exp")
	private int yearsOfExp;
	
	@Column(name = "technology_name")
	private String technologyName;
	
	@Column(name = "salary")
	private double salary;
	
	@OneToOne
	@JoinColumn(name="mt_user_id",referencedColumnName="id")
	private MTUser mtUser;
	
	
	
	public UserEmployementDetails() {
		super();
	}
	public UserEmployementDetails(String companyName, String designation, Date dateOfJoining, int yearsOfExp,
			String technologyName, double salary, MTUser mtUser) {
		super();
		this.companyName = companyName;
		this.designation = designation;
		this.dateOfJoining = dateOfJoining;
		this.yearsOfExp = yearsOfExp;
		this.technologyName = technologyName;
		this.salary = salary;
		this.mtUser = mtUser;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public int getYearsOfExp() {
		return yearsOfExp;
	}
	public void setYearsOfExp(int yearsOfExp) {
		this.yearsOfExp = yearsOfExp;
	}
	public String getTechnologyName() {
		return technologyName;
	}
	public void setTechnologyName(String technologyName) {
		this.technologyName = technologyName;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public MTUser getMtUser() {
		return mtUser;
	}
	public void setMtUser(MTUser mtUser) {
		this.mtUser = mtUser;
	}
	
	
}
