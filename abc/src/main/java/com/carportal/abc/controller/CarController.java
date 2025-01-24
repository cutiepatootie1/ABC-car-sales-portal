package com.carportal.abc.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.carportal.abc.dao.Bidding;
import com.carportal.abc.dao.Cars;
import com.carportal.abc.dao.User;
import com.carportal.abc.service.BIdService;
import com.carportal.abc.service.CarService;
import com.carportal.abc.service.RegService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;

@Controller
public class CarController {
	
	private static final String UPLOAD_DIRECTORY ="/images";
	private static Logger logger = LoggerFactory.getLogger(CarController.class);
	
	@Autowired
	private CarService carService;

	@Autowired
	private BIdService bidService;
	
	@Autowired
	private RegService regService;
	
	@GetMapping("/")
	public String handleRootReq(Model model) {
		return "home";
	}
	
	@GetMapping("cars")
	@PreAuthorize("hasAnyRole('user', 'admin')")
	public String viewCars(Model model) {
		List<Cars> cars = carService.getAllCars();
		if(!CollectionUtils.isEmpty(cars)) {
			model.addAttribute("cars", cars);
		}
		return "all_cars";
	}
	
	@GetMapping("/reg_car")
	@PreAuthorize("hasAnyRole('user', 'admin')")
	public String regCarForm(Map<String, Object> model) {
		System.out.println("Displaying car selling form");
		Cars cars = new Cars();
		model.put("cars", cars);
		return "reg_car";
	}
	
	@PostMapping("registered_car")
	@PreAuthorize("hasAnyRole('user','admin')")
	public String saveCar(Cars cars,@RequestParam("file")MultipartFile file, HttpSession session,
			Model model)throws Exception {
		System.out.println("acquiring file name");
		String carphotoname = file.getOriginalFilename();
		System.out.println("File name acquired...:" + carphotoname);
		
		if(carphotoname != null && !carphotoname.trim().isEmpty()) {
			cars.setCarphoto(carphotoname);
			
			//uploads car photo
			ServletContext context = session.getServletContext();
			String path = context.getRealPath(UPLOAD_DIRECTORY);
			String filename = file.getOriginalFilename();
			
			System.out.println(path + " " + filename);
			byte[] bytes = file.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File(path + File.separator + filename)));
			
			stream.write(bytes);
			stream.flush();
			stream.close();
			
			System.out.println("file successfuly saved!");

		}
		
		System.out.println("save & update new car");
		carService.saveCar(cars);
		return "redirect:cars";
	}
	
	// FOR BIDDING SERVICES//
	@GetMapping("/cardetails")
	@PreAuthorize("hasAnyRole('user','admin')")
	public ModelAndView viewcar(@RequestParam long id, Model model) {
		ModelAndView mav = new ModelAndView("cardetails");
		Cars cars = carService.get(id);
		mav.addObject("cars", cars);
		mav.addObject("bidinfo", bidService.listBidInfo(cars));
		
		//supposed to calculate and pass remaining time to view
		if(cars.getBidEndTime() != null) {
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime bidEndTime = cars.getBidEndTime();
			
			Duration remainingDuration = Duration.between(now, bidEndTime);
			long days = remainingDuration.toDaysPart();
			long hours = remainingDuration.toHoursPart();
			long minutes = remainingDuration.toMinutesPart();
			long seconds = remainingDuration.toSecondsPart();
			
			model.addAttribute("remainingDays",days);
			model.addAttribute("remainingHours",hours);
			model.addAttribute("remainingMinutes", minutes);
			model.addAttribute("remainingSeconds", seconds);
		}
		return mav;
	}
	
	@GetMapping("/timeRemaining")
	@ResponseBody
    public Map<String,Long> getTimeRemaining(@RequestParam long id) {
        Cars cars = carService.get(id);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime bidEndTime = cars.getBidEndTime();
        
        Duration remainingDuration = Duration.between(now, bidEndTime);
        long days = remainingDuration.toDaysPart();
        long hours = remainingDuration.toHoursPart();
        long minutes = remainingDuration.toMinutesPart();
        long seconds = remainingDuration.toSecondsPart();
        
        Map<String, Long> timeRemaining = new HashMap<>();
        timeRemaining.put("remainingDays", days);
        timeRemaining.put("remainingHours", hours);
        timeRemaining.put("remainingMinutes", minutes);
        timeRemaining.put("remainingSeconds", seconds);
        return timeRemaining;
    }
	
	//saves bidding info to car bidding page
	@PostMapping("/cardetails")
	@PreAuthorize("hasAnyRole('user','admin')")
	public String saveBid(@RequestParam(value="id", required = false)Long id,
			@RequestParam("bidprice")String bidprice, Model model) {
		//save bid info to db
		String email = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			email = ((UserDetails) principal).getUsername();
		} else {
			email = principal.toString();
		}
		
		Long carid= id;
		Cars cars = carService.get(id);
		User user = regService.getUserByEmail(email);
		
		Date curr_time = new Date();
		
		Bidding biddingInfo = new Bidding();
		biddingInfo.setBidderName(email);
		biddingInfo.setBiddingPrice(bidprice);
		biddingInfo.setCars(cars);
		biddingInfo.setUser(user);
		biddingInfo.setBiddingTime(curr_time);
		
		//check bid times
		if(cars.getBidStartTime()==null) {
			cars.setBidStartTime(LocalDateTime.now());
			cars.setBidEndTime(cars.getBidStartTime().plusDays(7));
		}
		
		logger.debug("Car bidding price:{}, Car id: {}", biddingInfo.getBiddingPrice(), biddingInfo.getId(),
				biddingInfo.getBidderName(), biddingInfo.getCars());
		
		Bidding savedCar = bidService.save(biddingInfo);
		
		model.addAttribute("cars", cars);
		model.addAttribute("bidinfo", bidService.listBidInfo(cars));
		return "cardetails";
	}
	
	//display update form with old data
	@GetMapping("/edit")
	@PreAuthorize("hasAnyRole('user','admin')")
	public String editCarForm(@RequestParam long id, Model model) {
		System.out.println("Ready to update car details");
		Cars cars = carService.get(id);
		model.addAttribute("cars", cars);
		return "reg_car";
	}
	
	@GetMapping("/delete")
	@PreAuthorize("hasAnyRole('user','admin')")
	public String deleteCar(@RequestParam long id) {
		carService.delete(id);
		return "redirect:/cars";
	}
	
	@GetMapping("/search")
	@PreAuthorize("hasAnyRole('user','admin')")
	public ModelAndView search(@RequestParam String keyword) {
		List<Cars> result = carService.search(keyword);
		ModelAndView mav = new ModelAndView("search_results");
		mav.addObject("keyword", keyword);
		mav.addObject("search_cars",result);
		return mav;
	}
}
