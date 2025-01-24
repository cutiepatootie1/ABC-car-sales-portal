package com.carportal.abc.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carportal.abc.controller.CarController;
import com.carportal.abc.dao.Cars;
import com.carportal.abc.repository.CarRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CarService {

	private static final Logger logger = LoggerFactory.getLogger(CarController.class);
	
	@Autowired
	private CarRepository carRepo;
	
	public CarService(CarRepository carRepo) {
		this.carRepo=carRepo;
	}
	
	public List<Cars> getAllCars(){
		System.out.println("Retrieving all car details");
		return carRepo.findAll();
	}
	
	public void saveCar(Cars cars) {
		carRepo.save(cars);
	}
	
	public Cars get(Long id) {
		return carRepo.findById(id).get();
	}
	
	public void delete(Long id) {
		carRepo.deleteById(id);
	}
	
	public List<Cars> search(String keyword){
		return carRepo.search(keyword);
	}
	
//	public List<Cars> getRemainingTime(){
//		return carRepo.get
//	}
}
