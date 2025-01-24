package com.carportal.abc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carportal.abc.dao.Bidding;
import com.carportal.abc.dao.Cars;
import com.carportal.abc.repository.BidRepository;
import com.carportal.abc.repository.CarRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class BIdService {

	@Autowired
	private BidRepository bidRepo;
	@Autowired
	private CarRepository carRepo;
	
	public Bidding save(Bidding bidding) {
		return bidRepo.save(bidding);
	}
	
	public List<Bidding> listAll(){
		return (List<Bidding>) bidRepo.findAll();
	}
	
	public List<Bidding> listBidInfo(Cars cars){
		return (List<Bidding>) bidRepo.findByCarId(cars);
	}
	
//	public Cars findById(Long id) {
//		return carRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("bid not found"));
//	}
}
