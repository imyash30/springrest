package com.spring.boot.service;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.dao.MTUserDao;
import com.spring.boot.dao.UserEducationDao;
import com.spring.boot.dto.UserDetailsDto;
import com.spring.boot.dto.UserEducationDto;
import com.spring.boot.entity.MTUser;
import com.spring.boot.entity.UserEducation;

@Service
public class UserEducationService {
	
	@Autowired
	UserEducationDao userEducationDao;
	
	@Autowired
	MTUserDao mtUserDao;
	
	@Autowired
	ModelMapper modelMapper;

	public boolean saveUserEducationList(Long userId, @Valid List<UserEducationDto> educationDtoList) {
		// TODO Auto-generated method stub
		
		MTUser mtUser = mtUserDao.getUserByID(userId);
		if(mtUser != null) {
			
			for(UserEducationDto eduDto : educationDtoList) {
				UserEducation userEdu = new UserEducation();
				userEdu.setQualification(eduDto.getQualification());
				userEdu.setPassedYear(Integer.parseInt(eduDto.getPassedYear()));
				userEdu.setCertification(eduDto.getCertification());
				
				userEdu.setMtUser(mtUser);
				userEducationDao.save(userEdu);
			}
			
			return true;
		}
		return false;
	}

	public List<UserEducationDto> getAllUsersEduDetails() {
		// TODO Auto-generated method stub
		List<UserEducation> educationList = userEducationDao.findAllByIsActive("Y");
		
		List<UserEducationDto> userDtoList = modelMapper.map(educationList, new TypeToken<List<UserEducationDto>>() {}.getType());
		
		return userDtoList;
	}

}
