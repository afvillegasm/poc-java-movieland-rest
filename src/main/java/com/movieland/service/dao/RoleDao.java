package com.movieland.service.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieland.service.entity.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, BigInteger>{

	public List<Role> findByName(final String name);
	public List<Role> findByStatus(final String status);
	
}
