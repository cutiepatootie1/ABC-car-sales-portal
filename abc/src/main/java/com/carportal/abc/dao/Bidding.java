package com.carportal.abc.dao;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="car_bidding")
public class Bidding {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="car_id")
	private Cars cars;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="bidder_name")
	private String bidderName;
	@Column(name="bidding_price")
	private String biddingPrice;
	@Column(name="bidding_date_time")
	private Date biddingTime;
	
	private Date bidEndTime;
	
	public Date getBidEndTime() {
		return bidEndTime;
	}

	public void setBidEndTime(Date bidEndTime) {
		this.bidEndTime = bidEndTime;
	}

	public Bidding() {
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Cars getCars() {
		return cars;
	}
	public void setCars(Cars cars) {
		this.cars = cars;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getBidderName() {
		return bidderName;
	}
	public void setBidderName(String bidderName) {
		this.bidderName = bidderName;
	}
	public String getBiddingPrice() {
		return biddingPrice;
	}
	public void setBiddingPrice(String biddingPrice) {
		this.biddingPrice = biddingPrice;
	}
	public Date getBiddingTime() {
		return biddingTime;
	}
	public void setBiddingTime(Date biddingTime) {
		this.biddingTime = biddingTime;
	}
	
	
	
	
}
