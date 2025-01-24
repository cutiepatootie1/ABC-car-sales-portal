package com.carportal.abc.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carportal.abc.dao.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	@Query("select r from Role r where r.role in :roles")
	Set<Role> findBySpecificRoles(@Param("roles") String role);

	
}
