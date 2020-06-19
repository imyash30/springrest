package com.spring.boot.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "mt_user")
public class MTUser extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "username")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@OneToOne(mappedBy = "mtUser", fetch = FetchType.LAZY)
	@JsonIgnore
	private UserDetails userDetails;
	
	@OneToOne(mappedBy = "mtUser", fetch = FetchType.LAZY)
	@JsonIgnore
	private UserEmployementDetails userEmployementDetails;
	
	@OneToMany(mappedBy = "mtUser", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<UserEducation> userEducationList;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public UserEmployementDetails getUserEmployementDetails() {
		return userEmployementDetails;
	}

	public void setUserEmployementDetails(UserEmployementDetails userEmployementDetails) {
		this.userEmployementDetails = userEmployementDetails;
	}

	public List<UserEducation> getUserEducationList() {
		return userEducationList;
	}

	public void setUserEducationList(List<UserEducation> userEducationList) {
		this.userEducationList = userEducationList;
	}

	

	
	
	
	

}
