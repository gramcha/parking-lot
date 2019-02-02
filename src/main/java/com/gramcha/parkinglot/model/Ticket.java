package com.gramcha.parkinglot.model;

public class Ticket {
	private String registrationNumber;
	private String color;
	private int allottedSlot;
	boolean isAlloted;

	public Ticket(String registrationNumber, String color, int allottedSlot, boolean isAlloted) {
		this.registrationNumber = registrationNumber;
		this.color = color;
		this.allottedSlot = allottedSlot;
		this.isAlloted = isAlloted;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public String getColor() {
		return color;
	}

	public int getAllottedSlot() {
		return allottedSlot;
	}

	public boolean isAlloted() {
		return isAlloted;
	}

	public String toString() {
		if (allottedSlot == -1) {
			return "Sorry, parking lot is full";
		} else {
			return "Allocated slot number: " + getAllottedSlot();
		}
	}
}
