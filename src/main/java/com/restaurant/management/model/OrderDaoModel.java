package com.restaurant.management.model;

public class OrderDaoModel {
	private String customerId;
	private String orderId;
	private int placedOrderTime;
	private int journeyTime;
	
	public OrderDaoModel(String customerId, String orderId, int placedOrderTime, int journeyTime) {
		super();
		this.customerId = customerId;
		this.orderId = orderId;
		this.placedOrderTime = placedOrderTime;
		this.journeyTime = journeyTime;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getPlacedOrderTime() {
		return placedOrderTime;
	}
	public void setPlacedOrderTime(int placedOrderTime) {
		this.placedOrderTime = placedOrderTime;
	}
	public int getJourneyTime() {
		return journeyTime;
	}
	public void setJourneyTime(int journeyTime) {
		this.journeyTime = journeyTime;
	}
	
	
}