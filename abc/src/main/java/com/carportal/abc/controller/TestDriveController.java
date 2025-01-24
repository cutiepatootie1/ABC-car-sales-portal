package com.carportal.abc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.carportal.abc.dao.Cars;
import com.carportal.abc.dao.User;
import com.carportal.abc.service.ApptService;
import com.carportal.abc.service.CarService;
import com.carportal.abc.service.RegService;

@Controller
public class TestDriveController {

	@Autowired
	private CarService carService;
	
	@Autowired
	private RegService regService;
	
	@Autowired
	private ApptService apptService;
	
	@GetMapping("/test_drive")
	@PreAuthorize("hasRole('admin', 'user')")
	public ModelAndView bookPage(@RequestParam long id) {
		
		ModelAndView mav = new ModelAndView("test_drive");
		Cars cars = carService.get(id);
		User user = regService.get(id);
		mav.addObject("cars",cars);
		mav.addObject("apptInfo", apptService.listApptInfo(user));
		return mav;
	}
	
//	@PostMapping("/book")
//	@PreAuthorize("hasRole('admin', 'user')")
//	public String saveBooking() {
//		
//	}
}
