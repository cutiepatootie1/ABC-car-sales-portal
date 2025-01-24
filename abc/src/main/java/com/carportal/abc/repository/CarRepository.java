package com.carportal.abc.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carportal.abc.dao.Cars;

@Repository
public interface CarRepository extends JpaRepository<Cars, Long> {

	@Query(value = "SELECT  c from Cars c WHERE c.make LIKE '%' || :keyword || '%'"
			+ "OR c.model LIKE '%' || :keyword || '%'"
			+ "OR c.price LIKE '%' || :keyword || '%'")
	public List<Cars> search(@Param("keyword") String keyword);
	

}
