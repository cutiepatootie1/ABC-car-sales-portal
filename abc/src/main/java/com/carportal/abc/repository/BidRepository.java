package com.carportal.abc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carportal.abc.dao.Bidding;
import com.carportal.abc.dao.Cars;

@Repository
public interface BidRepository extends JpaRepository<Bidding, Long>{

	@Query("SELECT b FROM Bidding b where b.cars in :car_id")
	List<Bidding> findByCarId(@Param("car_id") Cars cars);

//	@Query("DELETE FROM Bidding WHERE b.user in :user_id")
//	void deleteAllByUserId(@Param("user")Long id);
}
