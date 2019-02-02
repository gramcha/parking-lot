package com.gramcha.parkinglot.model;

import java.util.HashMap;
import java.util.TreeSet;
import java.util.Map;
import java.util.UUID;

public class ParkingLot {
	private int numberOfSlots;
	private String id;// will be useful when supporting more than one parking lot.\
	private Map<Integer, Car> allotedSlots;
	private TreeSet<Integer> sortedFreeSlots;

	public ParkingLot(int numberOfSlots) {
		allotedSlots = new HashMap<>();
		sortedFreeSlots = new TreeSet<>();
		init(numberOfSlots);
	}

	private void init(int numberOfSlots) {
		this.numberOfSlots = numberOfSlots;
		
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
		return sortedFreeSlots.size();
	}

	public Ticket allocateSlot(Car car) {
		Integer availableFreeSlot = getAvailableFreeSlotCloserToEntry();
		if(availableFreeSlot==null) {
			return getRejectionTicket(car);
		}
		allotedSlots.put(availableFreeSlot, car);
		return getAllocatedTicket(car, availableFreeSlot);
	}

	private Ticket getAllocatedTicket(Car car, Integer availableFreeSlot) {
		Ticket ticket = new Ticket(car.getRegistrationNumber(),car.getColor(),availableFreeSlot,true);
		return ticket;
	}

	private Ticket getRejectionTicket(Car car) {
		Ticket ticket = new Ticket(car.getRegistrationNumber(),car.getColor(),-1,false);
		return ticket;
	}
	private Integer getAvailableFreeSlotCloserToEntry() {
		return sortedFreeSlots.pollFirst();
	}
	
	public Ticket deallocateSlot(int slotNumber) {
		Ticket ticket = null;
		if((slotNumber>0 && slotNumber<= numberOfSlots)==false)
			return ticket;
		if(allotedSlots.containsKey(slotNumber)) {
			Car car = allotedSlots.remove(slotNumber);
			ticket = new Ticket(car.getRegistrationNumber(),car.getColor(),slotNumber,false);
		}
		sortedFreeSlots.add(slotNumber);
		return ticket;
	}
}
