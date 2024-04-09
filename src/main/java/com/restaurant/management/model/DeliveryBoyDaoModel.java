package com.restaurant.management.model;

public class DeliveryBoyDaoModel {
	public static final String ID_PREFIX = "D";
	private Status status;
	private int currentTaskEndTime=-1;
	private String customerId;
	private String id;
	
	
	
	public int getCurrentTaskEndTime() {
		return currentTaskEndTime;
	}
	public void setCurrentTaskEndTime(int currentTaskEndTime) {
		this.currentTaskEndTime = currentTaskEndTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status notAssigned) {
		this.status = notAssigned;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	
}
