package com.spring.boot.dto;

import java.util.List;

public class UserMasterDto {
	
	private String userName;
	private String isActive;
	
	private UserDetailsDto userDetails;
	private List<UserEducationDto> userEducationList;
	private UserEmployementDto userEmployementDetails;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public UserDetailsDto getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDetailsDto userDetails) {
		this.userDetails = userDetails;
	}
	public List<UserEducationDto> getUserEducationList() {
		return userEducationList;
	}
	public void setUserEducationList(List<UserEducationDto> userEducationList) {
		this.userEducationList = userEducationList;
	}
	public UserEmployementDto getUserEmployementDetails() {
		return userEmployementDetails;
	}
	public void setUserEmployementDetails(UserEmployementDto userEmployementDetails) {
		this.userEmployementDetails = userEmployementDetails;
	}
	
	

}
