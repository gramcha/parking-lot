package com.gramcha.parkinglot.model;

public class Car {
	String registrationNumber;
	String color;

	Car(String registrationNumber, String color) {
		this.registrationNumber = registrationNumber;
		this.color = color;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public String getColor() {
		return color;
	}
	
}
