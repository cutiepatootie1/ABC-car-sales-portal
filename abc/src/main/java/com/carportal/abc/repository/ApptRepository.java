package com.carportal.abc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carportal.abc.dao.Appointment;
import com.carportal.abc.dao.User;

@Repository
public interface ApptRepository extends JpaRepository<Appointment, Long>{

	@Query("SELECT a FROM Appointment a WHERE a.user in :user_id")
	List<Appointment> findByApptId(@Param("user_id")User user);
}
