/*package com.spring.boot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.dto.UserDetailsDto;
import com.spring.boot.dto.UserDto;
import com.spring.boot.dto.UserEducationDto;
import com.spring.boot.service.UserEducationService;

@RestController
public class UserEducationController {
	
	@Autowired
	UserEducationService userEducationService;
	
	@GetMapping("/getUsersEducationList")
	public List<UserEducationDto> getUsersEducationList() {
		return userEducationService.getAllUsersEduDetails();
	}
	
	@PostMapping("/createEducationDetails/{id}")
	public ResponseEntity<?> createUser(@PathVariable(value = "id") Long userId,@Valid @RequestBody List<UserEducationDto> educationDtoList,BindingResult br) throws Exception {
		Map<String,Object> response = new HashMap<>();
		if(br.hasErrors()) {
			List<String> errors = br.getFieldErrors().stream().map(err -> "The field '" + err.getField() +"' "+ err.getDefaultMessage()) .collect(Collectors.toList());   
		      response.put("Errors",errors);
		      response.put("message", "Field validation");
		      return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}else {
			if(userEducationService.saveUserEducationList(userId,educationDtoList)) {
				response.put("message", "created successfully");
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
			}	
			else {
				response.put("message", "creation failed");
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
			}	
//			response.put("User", savedUser);
			
		}
	}

}
*/