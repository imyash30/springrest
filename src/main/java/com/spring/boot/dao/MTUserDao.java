package com.spring.boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.boot.entity.MTUser;

public interface MTUserDao extends JpaRepository<MTUser, Long>{

	@Query(value="select s from MTUser s where s.id=?1")
	MTUser getUserByID(Long userId);

}
