package com.spring.boot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.entity.User;

public interface UserDao extends JpaRepository<User, Long>{

	List<User> findAllByFirstNameAndLastNameAndPincode(String firstName, String lastName, String pincode);

}
