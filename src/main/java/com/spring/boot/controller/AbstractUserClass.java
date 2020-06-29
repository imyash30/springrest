package com.spring.boot.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;
import com.spring.boot.dto.UserDto;
import com.spring.boot.entity.MTUser;
import com.spring.boot.exception.validateException;

abstract class AbstractUserClass {
	
	protected boolean valid(UserDto userDto) throws validateException {
		// TODO Auto-generated method stub
//		boolean isValid = false;
		
		List<String> errors = new ArrayList<>();
		if(!isValidUserName(userDto.getUserName())) {
			errors.add("Username invalid, must contain only lower case and digits");
		}
		if(!isValidPassword(userDto.getPassword())) {
			errors.add("Password Invalid, Enter min 8 character, 1 capital,1 number, No special char.");
		}
		if(!isValidName(userDto.getFirstName(),userDto.getLastName())) {
			errors.add("Invalid first or last name, only contain lower and upper case");
		}
		if(!isValidEmail(userDto.getEmail())) {
			errors.add("Email is not valid");
		}
		if(!isValidMobile(userDto.getMobile()))
			errors.add("Invalid Mobile number");
		
 		if(!isValidPincode(userDto.getPincode()))
 			errors.add("Pincode should contain 6 digits only");
 		
 		if(!isValidDobDoj(userDto.getDateOfBirth(),userDto.getDateOfJoining()))
 			errors.add("Birth date should be before joining date");
 		
 		if(errors.size() == 0)
 			return true;
 		else
 			throw new validateException("Not valid data", errors);
	}
	
	private boolean isValidDobDoj(String dateOfBirth, String dateOfJoining) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return sdf.parse(dateOfBirth).compareTo(sdf.parse(dateOfJoining)) <= 0;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private boolean isValidPincode(String pincode) {
		// TODO Auto-generated method stub
		return pincode.matches("([\\d]){6}");
	}

	private boolean isValidMobile(String mobile) {
		// TODO Auto-generated method stub
		return mobile.matches("(0/91)?[7-9][0-9]{9}");
	}

	private boolean isValidEmail(String email) {
		// TODO Auto-generated method stub
		return email.matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$");
	}

	private boolean isValidName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return firstName.matches("([a-zA-Z]){2,16}") && lastName.matches("([a-zA-Z]){2,16}");
	}

	private boolean isValidPassword(String password) {
		// TODO Auto-generated method stub
		return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$");
	}

	private boolean isValidUserName(String userName) {
		// TODO Auto-generated method stub
		return userName.matches("^[a-z0-9_-]{3,15}$");
	}

	protected ResponseEntity<Object> responseBuilder(boolean b) throws validateException {
		if(b == true) {
			return new ResponseEntity<>("user created", HttpStatus.OK);
		}
		return new ResponseEntity<>("creation failed", HttpStatus.BAD_REQUEST);
	}
	
	protected ResponseEntity<Object> responseBuilder(validateException ve) {
		Gson gson = new Gson();
		return new ResponseEntity<>(gson.toJson(ve), HttpStatus.BAD_REQUEST);
	}

}
