package com.gramcha.parkinglot.model;

import java.util.HashMap;
import java.util.TreeSet;
import java.util.Map;
import java.util.UUID;

public class ParkingLot {
	private int numberOfSlots;
	private String id;// will be useful when supporting more than one parking lot.
	private int numberOfFreeSlots;
	private Map<Integer, Car> allotedSlots;
	private TreeSet<Integer> sortedFreeSlots;

	public ParkingLot(int numberOfSlots) {
		allotedSlots = new HashMap<>();
		sortedFreeSlots = new TreeSet<>();
		init(numberOfSlots);
	}

	private void init(int numberOfSlots) {
		this.numberOfSlots = numberOfSlots;
		this.numberOfFreeSlots = numberOfSlots;
		this.id = generateUniqueIdForParkingLot();
		//as per requirement slot number starts from 1.
		for (int i = 1; i <= numberOfSlots; i++) {
			sortedFreeSlots.add(i);
		}
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

	public Ticket allocateSlot(Car car) {
		if(numberOfFreeSlots<1) {
			Ticket ticket = new Ticket(car.getRegistrationNumber(),car.getColor(),-1);
			return ticket;
		}
		Integer availableFreeSlot = getAvailableFreeSlotCloserToEntry();
		if(availableFreeSlot==null) {
			Ticket ticket = new Ticket(car.getRegistrationNumber(),car.getColor(),-1);
			return ticket;
		}
		allotedSlots.put(availableFreeSlot, car);
		numberOfFreeSlots--;
		Ticket ticket = new Ticket(car.getRegistrationNumber(),car.getColor(),availableFreeSlot);
		return ticket;
	}
	private Integer getAvailableFreeSlotCloserToEntry() {
		return sortedFreeSlots.pollFirst();
	}
}
