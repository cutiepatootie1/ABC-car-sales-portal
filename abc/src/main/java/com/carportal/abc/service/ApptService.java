package com.carportal.abc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carportal.abc.dao.Appointment;
import com.carportal.abc.dao.User;
import com.carportal.abc.repository.ApptRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ApptService {

	@Autowired
	private ApptRepository apptRepo;
	
	public Appointment save(Appointment appointment) {
		return apptRepo.save(appointment);
	}
	
	public void delete(Long id) {
		apptRepo.deleteById(id);
	}
	
	public List<Appointment> listAll(){
		return (List<Appointment>) apptRepo.findAll();
	}
	
	public List<Appointment> listApptInfo(User user){
		return (List<Appointment>) apptRepo.findByApptId(user);
	}
}
