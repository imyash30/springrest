package com.spring.boot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "user_details")
public class UserDetails extends BaseEntity {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "first name is mandatory")
	@Column(name = "first_name")
	private String firstName;
	
	@NotBlank(message = "last name is mandatory")
	@Column(name = "last_name")
	private String lastName;
	
	@NotBlank(message = "gender is mandatory")
	@Column(name = "gender")
	private String gender;
	
	@NotBlank(message = "age is mandatory")
	@Column(name = "age")
	private String age;
	
	@NotBlank @Email(message = "Please provide a valid email")
	@Column(name = "email")
	private String email;
	
	@Size(min=10,max=10,message="10 digits required")
	@Column(name = "mobile")
	private String	mobile;
	
	@Column(name = "address")
	private String	address;
	
	@Column(name = "d_o_b")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Past
	private Date dateOfBirth;
	
	@Size(min=6,max=6,message="6 digits only")
	@Column(name = "pincode")
	private String pincode;
	
	@OneToOne
	@JsonBackReference
	@JoinColumn(name="mt_user_id",referencedColumnName="id")
	private MTUser mtUser;
	
	
	public UserDetails(String firstName, String lastName, @Size(message = "required") String gender, String age,
			String email, @Size(min = 10, max = 10, message = "10 digits required") String mobile, String address,
			Date dateOfBirth, String pincode) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.pincode = pincode;
	}
	public UserDetails() {
		// TODO Auto-generated constructor stub
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public MTUser getMtUser() {
		return mtUser;
	}
	public void setMtUser(MTUser mtUser) {
		this.mtUser = mtUser;
	}
	
	
	
}
