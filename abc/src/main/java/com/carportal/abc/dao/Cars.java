package com.carportal.abc.dao;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="cars")
public class Cars {

	@Id
	@Column(name="car_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="make")
	private String make;
	@Column(name="model")
	private String model;
	@Column(name="registration")
	private String registration;
	@Column(name="price")
	private String price;
	
	private String carphoto;
	
	private LocalDateTime bidStartTime;
	public LocalDateTime getBidStartTime() {
		return bidStartTime;
	}

	public void setBidStartTime(LocalDateTime bidStartTime) {
		this.bidStartTime = bidStartTime;
	}

	private LocalDateTime bidEndTime;
	
	public LocalDateTime getBidEndTime() {
		return bidEndTime;
	}

	public void setBidEndTime(LocalDateTime bidEndTime) {
		this.bidEndTime = bidEndTime;
	}

	@OneToMany(mappedBy = "cars", orphanRemoval=true)
	private Set<Bidding> biddings = new HashSet<Bidding>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCarphoto() {
		return carphoto;
	}

	public void setCarphoto(String carphoto) {
		this.carphoto = carphoto;
	}

	public Set<Bidding> getBiddings() {
		return biddings;
	}

	public void setBiddings(Set<Bidding> biddings) {
		this.biddings = biddings;
	}
}
