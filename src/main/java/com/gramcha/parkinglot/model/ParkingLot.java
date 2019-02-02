package com.gramcha.parkinglot.model;

import java.util.UUID;

public class ParkingLot {
	int numberOfSlots;
	String id;
	int numberOfFreeSlots;
	
	public ParkingLot(int numberOfSlots) {
		this.numberOfSlots = numberOfSlots;
		this.id = generateUniqueIdForParkingLot();
	}

	String generateUniqueIdForParkingLot() {
		UUID uniqueKey = UUID.randomUUID();
		return uniqueKey.toString();
	}

	public int getNumberOfSlots() {
		return numberOfSlots;
	}

	public String getId() {
		return id;
	}

	public int getNumberOfFreeSlots() {
		return numberOfFreeSlots;
	}

}
