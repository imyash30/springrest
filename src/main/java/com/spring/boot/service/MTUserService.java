package com.spring.boot.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.dao.MTUserDao;
import com.spring.boot.dao.UserDetailsDao;
import com.spring.boot.dao.UserEducationDao;
import com.spring.boot.dao.UserEmployementDao;
import com.spring.boot.dto.SearchDto;
import com.spring.boot.dto.UserDetailsDto;
import com.spring.boot.dto.UserDto;
import com.spring.boot.dto.UserMasterDto;
import com.spring.boot.entity.MTUser;
import com.spring.boot.entity.UserDetails;
import com.spring.boot.entity.UserEducation;
import com.spring.boot.entity.UserEmployementDetails;
import com.spring.boot.exception.ResourceNotFoundException;

@Service
public class MTUserService {
	
	@Autowired
	MTUserDao mtUserDao;
	
	@Autowired
	UserDetailsDao userDetailsDao;
	
	@Autowired
	UserEmployementDao userEmployementDao;
	
	@Autowired
	UserEducationDao userEducationDao;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	EntityManager entityManager;

	public boolean saveUser(@Valid UserDto userDto) {
		// TODO Auto-generated method stub
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			MTUser user = new MTUser();
			user.setUserName(userDto.getUserName());
			user.setPassword(userDto.getPassword());
			mtUserDao.save(user);
			
			UserDetails userDetails = new UserDetails();
			userDetails.setFirstName(userDto.getFirstName());
			userDetails.setLastName(userDto.getLastName());
			userDetails.setGender(userDto.getGender());
			userDetails.setAge(userDto.getAge());
			userDetails.setEmail(userDto.getEmail());
			userDetails.setMobile(userDto.getMobile());
			userDetails.setAddress(userDto.getAddress());
			userDetails.setDateOfBirth(sdf.parse(userDto.getDateOfBirth()));
			userDetails.setPincode(userDto.getPincode());
			
			userDetails.setMtUser(user);
			userDetailsDao.save(userDetails);
			
			UserEducation userEducation = new UserEducation();
			userEducation.setQualification(userDto.getQualification());
			userEducation.setPassedYear(Integer.parseInt(userDto.getPassedYear()));
			userEducation.setCertification(userDto.getCertification());
			
			userEducation.setMtUser(user);
			userEducationDao.save(userEducation);
			
			UserEmployementDetails userEmployement = new UserEmployementDetails();
			userEmployement.setCompanyName(userDto.getCompanyName());
			userEmployement.setDesignation(userDto.getDesignation());
			userEmployement.setDateOfJoining(sdf.parse(userDto.getDateOfJoining()));
			userEmployement.setSalary(Double.valueOf(userDto.getSalary()));
			userEmployement.setTechnologyName(userDto.getTechnologyName());
			userEmployement.setYearsOfExp(Integer.valueOf(userDto.getYearsOfExp()));
			
			userEmployement.setMtUser(user);
			userEmployementDao.save(userEmployement);
			
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<MTUser> userList = mtUserDao.findAll();
		
		List<UserDto> userDtoList = modelMapper.map(userList, new TypeToken<List<UserDto>>() {}.getType());
		
		return userDtoList;
	}

	public UserDetailsDto updateUserDetails(Long userId, @Valid UserDetailsDto userDto) throws ParseException, ResourceNotFoundException {
		// TODO Auto-generated method stub
		UserDetails userDetails = userDetailsDao.getUserDetailsById(userId);
		if(userDetails != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			userDetails.setFirstName(userDto.getFirstName());
			userDetails.setLastName(userDto.getLastName());
			userDetails.setMobile(userDto.getMobile());
			userDetails.setAge(userDto.getAge());
			userDetails.setAddress(userDto.getAddress());
			userDetails.setDateOfBirth(sdf.parse(userDto.getDateOfBirth()));
			userDetails.setEmail(userDto.getEmail());
			userDetails.setGender(userDto.getGender());
			userDetails.setPincode(userDto.getPincode());
			
			userDetailsDao.save(userDetails);
			
//			UserDetailsDto userDetailsDto = modelMapper.map(userDetails, new TypeToken<UserDetailsDto>() {}.getType());
			
			return userDto;
		}else {
			throw new ResourceNotFoundException("User not found on :: " + userId);
//			return null;
		}
	}

	public List<UserDetailsDto> getAllUserByDobSorting() {
		// TODO Auto-generated method stub
		List<UserDetails> userdetailsList = userDetailsDao.findAllByIsActive("Y");
		
		Collections.sort(userdetailsList,new Comparator<UserDetails>() {

			@Override
			public int compare(UserDetails u1, UserDetails u2) {
				// TODO Auto-generated method stub
				return u1.getDateOfBirth().compareTo(u2.getDateOfBirth());
			}
			
		});
		
		List<UserDetailsDto> userDtoList = modelMapper.map(userdetailsList, new TypeToken<List<UserDetailsDto>>() {}.getType());
		return userDtoList;
	}

	public List<UserDetails> getUserByNameAndPincode(String firstName, String lastName, String pincode) {
		// TODO Auto-generated method stub
		List<UserDetails> userList = userDetailsDao.findAllByFirstNameAndLastNameAndPincode(firstName,lastName,pincode);
//		List<UserDetailsDto> userDtoList = modelMapper.map(userList, new TypeToken<List<UserDetailsDto>>() {}.getType());
		return userList;
	}

	public UserMasterDto getUserById(Long userId) {
		// TODO Auto-generated method stub
		MTUser user = mtUserDao.getUserByID(userId);
		
		UserMasterDto userDto = modelMapper.map(user, new TypeToken<UserMasterDto>() {}.getType());
		return userDto;
	}

	public List<UserMasterDto> getAllUsersDetails() {
		// TODO Auto-generated method stub
//		Map<String, Object> userdetails = new HashMap<String, Object>();
		
		List<MTUser> userList = mtUserDao.findAllByIsActive("Y");
		List<UserMasterDto> userDtoList = modelMapper.map(userList, new TypeToken<List<UserMasterDto>>() {}.getType());
		return userDtoList;
	}

	public boolean hardDeleteUser(Long userId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		MTUser user = mtUserDao.findById(userId)
			       .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
		if(user != null) {
			UserDetails userDetails = user.getUserDetails();
			List<UserEducation> userEducationList = user.getUserEducationList();
			UserEmployementDetails userEmployement = user.getUserEmployementDetails();
			if(userDetails != null)
				userDetailsDao.delete(userDetails);
			if(userEducationList != null && userEducationList.size() > 0)
				userEducationDao.deleteAll(userEducationList);
			if(userEmployement != null)
				userEmployementDao.delete(userEmployement);
			mtUserDao.delete(user);
			return true;
		}else {
			return false;
		}
	}

	public boolean softDeleteUser(Long userId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		MTUser user = mtUserDao.findById(userId)
			       .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
		if(user != null) {
			UserDetails userDetails = user.getUserDetails();
			List<UserEducation> userEducationList = user.getUserEducationList();
			UserEmployementDetails userEmployement = user.getUserEmployementDetails();
			if(userDetails != null) {
				userDetails.setIsActive("N");
				userDetailsDao.save(userDetails);
			}	
			if(userEducationList != null && userEducationList.size() > 0) {
				userEducationList.stream().forEach(e -> e.setIsActive("N"));
				userEducationDao.saveAll(userEducationList);
			}	
			if(userEmployement != null) {
				userEmployement.setIsActive("N");
				userEmployementDao.save(userEmployement);
			}
			user.setIsActive("N");
			mtUserDao.save(user);
			return true;
		}else {
			return false;
		}
	}

	public List<UserEmployementDetails> getAllUserByDojSorting() {
		// TODO Auto-generated method stub
		List<UserEmployementDetails> userEmpDetails = userEmployementDao.findAllByIsActive("Y");
		
		Collections.sort(userEmpDetails,new Comparator<UserEmployementDetails>() {

			@Override
			public int compare(UserEmployementDetails u1, UserEmployementDetails u2) {
				// TODO Auto-generated method stub
				return u1.getDateOfJoining().compareTo(u2.getDateOfJoining());
			}
			
		});
		
		return userEmpDetails;
	}

	public List<UserDetailsDto> getDetailsByDynamicSearch(List<SearchDto> searchDtoList) {
		// TODO Auto-generated method stub
		List<UserDetails> userDetailsList = new ArrayList<UserDetails>();
		StringBuilder sbquery = new StringBuilder();
		if(searchDtoList.size() > 0) {
			sbquery.append("select * from user_details ");
		
			sbquery.append("where ");
			for(SearchDto sdto : searchDtoList) {
				if(sdto.getKey().equalsIgnoreCase("firstname"))
					sbquery.append("first_name = '"+sdto.getValue()+"' and ");
				if(sdto.getKey().equalsIgnoreCase("lastname"))
					sbquery.append("last_name = '"+sdto.getValue()+"' and ");
				if(sdto.getKey().equalsIgnoreCase("address"))
					sbquery.append("address = '"+sdto.getValue()+"' and ");
				if(sdto.getKey().equalsIgnoreCase("pincode"))
					sbquery.append("pincode = '"+sdto.getValue()+"' and ");
			}
			String finalQuery = sbquery.substring(0, sbquery.length() - 4);
			
			Query query = entityManager.createNativeQuery(finalQuery,UserDetails.class);
			userDetailsList.addAll(query.getResultList());
		}
		List<UserDetailsDto> userDtoList = modelMapper.map(userDetailsList, new TypeToken<List<UserDetailsDto>>() {}.getType());
		return userDtoList;
	}

}
