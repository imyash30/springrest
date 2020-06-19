package com.spring.boot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.boot.entity.UserDetails;

public interface UserDetailsDao extends JpaRepository<UserDetails, Long>{

	@Query(value="select s from UserDetails s where s.id=?1")
	UserDetails getById(Long userId);

	@Query(value="select s from UserDetails s where s.mtUser.id=?1")
	UserDetails getUserDetailsById(Long userId);

	List<UserDetails> findAllByIsActive(String string);
	
	List<UserDetails> findAllByFirstNameAndLastNameAndPincode(String firstName, String lastName, String pincode);

}
