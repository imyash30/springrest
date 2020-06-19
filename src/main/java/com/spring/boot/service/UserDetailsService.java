/*package com.spring.boot.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.dao.MTUserDao;
import com.spring.boot.dao.UserDetailsDao;
import com.spring.boot.dto.UserDto;
import com.spring.boot.entity.MTUser;
import com.spring.boot.entity.UserDetails;
import com.spring.boot.exception.ResourceNotFoundException;

@Service
public class UserDetailsService {
	
	@Autowired
	MTUserDao mtUserDao;
	
	@Autowired
	ModelMapper modelMapper;

	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<MTUser> userList = mtUserDao.findAll();
		
		List<UserDto> userDtoList = modelMapper.map(userList, new TypeToken<List<UserDto>>() {}.getType());
		
		return userDtoList;
	}

	public UserDetails saveUser(@Valid UserDetails user) {
		// TODO Auto-generated method stubx
		return userDao.save(user);
	}

	public UserDetails getUser(Long userId) {
		// TODO Auto-generated method stub
		return userDao.getById(userId);
	}

	public UserDetails updateUser(Long userId, @Valid UserDetails userDetails) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		 UserDetails user = userDao.findById(userId)
			       .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
		 
		 user.setFirstName(userDetails.getFirstName());
		 user.setLastName(userDetails.getLastName());
		 user.setMobile(userDetails.getMobile());
		 user.setAge(userDetails.getAge());
		 user.setAddress(userDetails.getAddress());
		 user.setDateOfBirth(user.getDateOfBirth());
//		 user.setDateOfJoining(userDetails.getDateOfJoining());
		 user.setEmail(userDetails.getEmail());
		 user.setGender(userDetails.getGender());
		 user.setPincode(userDetails.getPincode());
		 userDao.save(user);
		return user;
	}

	public boolean deleteUser(Long userId) throws ResourceNotFoundException  {
		// TODO Auto-generated method stub
		UserDetails user = userDao.findById(userId)
			       .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
		if(user != null) {
			userDao.delete(user);
			return true;
		}else {
			return false;
		}
	}

	public List<UserDetails> getUserByNameAndPincode(String firstName, String lastName, String pincode) {
		// TODO Auto-generated method stub
		List<UserDetails> userList = userDao.findAllByFirstNameAndLastNameAndPincode(firstName,lastName,pincode);
		return userList;
	}

	public List<UserDto> getAllUserByDobSorting() {
		// TODO Auto-generated method stub
		List<UserDetails> userList = userDao.findAll();
		Collections.sort(userList, new Comparator<UserDetails>(){
			@Override
			public int compare(UserDetails u1, UserDetails u2) {
				// TODO Auto-generated method stub
				return (u2.getDateOfBirth()).compareTo(u1.getDateOfBirth());
			}
			
		});
		List<UserDto> userDtoList = modelMapper.map(userList, new TypeToken<List<UserDto>>() {}.getType());
		return userDtoList;
	}

	public UserDto getUserById(Long userId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		UserDetails user = userDao.getById(userId);
		
		UserDto userDto = modelMapper.map(user, new TypeToken<UserDto>() {}.getType());
		
		return userDto;
	}
	
	public UserDetails getUserByIdForTest(Long userId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		UserDetails user = userDao.getById(userId);
		
		return user;
	}
	

}
*/