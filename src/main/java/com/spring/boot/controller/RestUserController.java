package com.spring.boot.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.dto.UserDetailsDto;
import com.spring.boot.dto.UserDto;
import com.spring.boot.entity.MTUser;
import com.spring.boot.entity.UserDetails;
import com.spring.boot.entity.UserEmployementDetails;
import com.spring.boot.exception.ResourceNotFoundException;
import com.spring.boot.service.MTUserService;

@RestController
public class RestUserController {
	
	private static final Logger logger = LoggerFactory.getLogger(RestUserController.class);

	
	@Autowired
	MTUserService mtUserService;
	
	@GetMapping("/getAllUsersDetails")
	public List<UserDetailsDto> getAllUsers() {
		logger.info("Fetching all user details");
		return mtUserService.getAllUsersDetails();
	}
	
	/*
	 * create user and mapping details through dto
	 */
	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto,BindingResult br) throws Exception {
		Map<String,Object> response = new HashMap<>();
		if(br.hasErrors()) {
			List<String> errors = br.getFieldErrors().stream().map(err -> "The field '" + err.getField() +"' "+ err.getDefaultMessage()) .collect(Collectors.toList());   
		      response.put("Errors",errors);
		      response.put("message", "Field validation");
		      logger.info("validation error in creating user");
		      return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}else {
			if(mtUserService.saveUser(userDto)) {
				response.put("message", "created successfully");
				logger.info("usre created and all mapping data are also created");
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
			}	
			else {
				response.put("message", "creation failed");
				logger.info("creation failed");
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
			}	
//			response.put("User", savedUser);
			
		}
	}
	
	/*
	 * Update user details based on user id.
	 */
	@PutMapping("/updateUserDetails/{id}")
	public ResponseEntity<UserDetailsDto> updateUser( @PathVariable(value = "id") Long userId, @Valid @RequestBody UserDetailsDto userDto) throws ResourceNotFoundException, ParseException {
		logger.info("update user details method starts ");
		final UserDetailsDto updatedUser = mtUserService.updateUserDetails(userId,userDto);
		return ResponseEntity.ok(updatedUser);
	}

	/*
	 * Get user by id.
	 */
	@GetMapping("/getUserById/{id}")
	public MTUser getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		logger.info("Fetching user based on id");
		return mtUserService.getUserById(userId);
	}

	/*
	 * hard delete, It delete recored based on user id.
	 */
	@DeleteMapping("/hardDeleteUser/{id}")
	public ResponseEntity<?> hardDeleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException{
		Map<String,Object> response = new HashMap<>();
		if(mtUserService.hardDeleteUser(userId)) {
			response.put("message", "User deleted successfully");
			logger.info("Hard delete user executed");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		}else {
			response.put("message", "delete user failed");
			logger.info("Hard delete failed");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}	
	}
	
	/*
	 * Soft delete, It changes active status to N.
	 */
	@DeleteMapping("/softDeleteUser/{id}")
	public ResponseEntity<?> softDeleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException{
		Map<String,Object> response = new HashMap<>();
		if(mtUserService.softDeleteUser(userId)) {
			response.put("message", "User deleted successfully");
			logger.info("soft delete method executed");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		}else {
			response.put("message", "delete user failed");
			logger.info("soft delete failed");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}	
	}
	
	/*
	 * Fetch user details based on parameters.
	 */
	@PostMapping("/getUserByNameAndPincode")
	public List<UserDetails> getUserByNameAndPincode(@RequestParam("firstName")String firstName,
			@RequestParam("lastName")String lastName,@RequestParam("pincode")String pincode){
		logger.info("Fetching user details based on parameters given");
		List<UserDetails> userList= mtUserService.getUserByNameAndPincode(firstName,lastName,pincode);
		return userList;
	}
	
	/*
	 * Sort user details based on DOB.
	 */
	@GetMapping("/getAllUserByDobSorting")
	public List<UserDetails> getAllUserByDobSorting(){
		logger.info("Fetching all user details by sorting dob");
		List<UserDetails> userList= mtUserService.getAllUserByDobSorting();
		return userList;
	}

	/*
	 * Sort user employement details based on date of joining.
	 */
	@GetMapping("/getAllUserByDojSorting")
	public List<UserEmployementDetails> getAllUserByDojSorting(){
		logger.info("Fetching user employement info by sorting doj");
		List<UserEmployementDetails> userList= mtUserService.getAllUserByDojSorting();
		return userList;
	}
	
	/*@PostMapping("/createUserDetails")
	public ResponseEntity<?> createUserDetails(@Valid @RequestBody UserDetails user,BindingResult br) {
		Map<String,Object> response = new HashMap<>();
		if(br.hasErrors()) {
			List<String> errors = br.getFieldErrors().stream().map(err -> "The field '" + err.getField() +"' "+ err.getDefaultMessage()) .collect(Collectors.toList());   
		      response.put("Errors",errors);
		      response.put("message", "Field validation");
		      return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}else {
			UserDetails savedUser = userService.saveUser(user);
			response.put("message", "created successfully");
			response.put("UserDetails", savedUser);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
		}
	}*/
	
	

}
