package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Donor {
	@Id
	@GeneratedValue
	private String id ;
	private String donorName;
	private String  bloodGp;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDonorName() {
		return donorName;
	}
	public void setDonorName(String donorName) {
		this.donorName = donorName;
	}
	public String getBloodGp() {
		return bloodGp;
	}
	public void setBloodGp(String bloodGp) {
		this.bloodGp = bloodGp;
	}
	
}
