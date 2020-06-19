package com.spring.boot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.entity.UserEducation;

public interface UserEducationDao extends  JpaRepository<UserEducation,Long>{

	List<UserEducation> findAllByIsActive(String string);

}
