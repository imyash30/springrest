package com.spring.boot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import com.spring.boot.dto.UserDto;
import com.spring.boot.entity.User;
import com.spring.boot.exception.ResourceNotFoundException;
import com.spring.boot.service.UserService;

@RestController
public class RestUserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/getAllUsers")
	public List<UserDto> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user,BindingResult br) {
		Map<String,Object> response = new HashMap<>();
		if(br.hasErrors()) {
			List<String> errors = br.getFieldErrors().stream().map(err -> "The field '" + err.getField() +"' "+ err.getDefaultMessage()) .collect(Collectors.toList());   
		      response.put("Errors",errors);
		      response.put("message", "Field validation");
		      return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}else {
			User savedUser = userService.saveUser(user);
			response.put("message", "created successfully");
			response.put("User", savedUser);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
		}
	}
	
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<User> updateUser( @PathVariable(value = "id") Long userId, @Valid @RequestBody User user) throws ResourceNotFoundException {
		
		final User updatedUser = userService.updateUser(userId,user);
		return ResponseEntity.ok(updatedUser);
	}

	@GetMapping("/getUserById/{id}")
	public UserDto getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		return userService.getUserById(userId);
	}

	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException{
		Map<String,Object> response = new HashMap<>();
		if(userService.deleteUser(userId)) {
			response.put("message", "User deleted successfully");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		}else {
			response.put("message", "delete user failed");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}	
	}
	
	@PostMapping("/getUserByNameAndPincode")
	public List<User> getUserByNameAndPincode(@RequestParam("firstName")String firstName,
			@RequestParam("lastName")String lastName,@RequestParam("pincode")String pincode){
		
		List<User> userList= userService.getUserByNameAndPincode(firstName,lastName,pincode);
		return userList;
	}
	
	@GetMapping("/getAllUserByDobSorting")
	public List<UserDto> getAllUserByDobSorting(){
		
		List<UserDto> userList= userService.getAllUserByDobSorting();
		return userList;
	}

}
