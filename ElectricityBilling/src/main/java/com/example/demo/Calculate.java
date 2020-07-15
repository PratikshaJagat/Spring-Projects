package com.example.demo;

public class Calculate implements Constants{

	double amount;
	String userType;
	double units;
	
	public double getAmount(String uType, double unit) {
	
		units = unit;
		userType =uType;
		 if (userType.equals("commercial")) {
			 amount = units*cCharge;
			 return amount;
		 }
		 else if (userType.equals("industrial")) {
			 amount = units*iCharge;
			 return amount;

		 }
		 else {
			 amount = units*hCharge;
			 return amount;
		 }
		
	}
}
