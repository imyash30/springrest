package com.spring.boot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.entity.UserEmployementDetails;

public interface UserEmployementDao extends JpaRepository<UserEmployementDetails, Long> {

	List<UserEmployementDetails> findAllByIsActive(String string);

}
