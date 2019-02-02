package com.gramcha.parkinglot.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.gramcha.parkinglot.model.Car;
import com.gramcha.parkinglot.model.ParkingLot;
import com.gramcha.parkinglot.model.Ticket;
import com.gramcha.parkinglot.service.IndexAndQueryService;
import com.gramcha.parkinglot.service.ParkingLotService;

public class DefaultParkingLotService implements ParkingLotService{

	//this is to support future requirement like having more than one parking lot.
	Map<String, ParkingLot> parkingLots = new HashMap<>();
	
	//this holds the recently created parking lot - as per current requirement single parking lot.
	ParkingLot currentLot; 

	IndexAndQueryService indexAndQueryService = new DefaultIndexAndQueryService();
	
	public ParkingLot createParkingLot(int noOfParkingSlots) {
		// TODO Auto-generated method stub
		ParkingLot newParkingLot = new ParkingLot(noOfParkingSlots);
		parkingLots.put(newParkingLot.getId(), newParkingLot);
		currentLot = newParkingLot;
		return newParkingLot;
	}
	
	public Ticket allocateSlot(Car car) {
		Ticket ticket = currentLot.allocateSlot(car);
		indexAndQueryService.addIndex(ticket);
		return ticket;
	}
	public String deallocateSlot(int slotNumber) {
		Ticket ticket = currentLot.deallocateSlot(slotNumber);
		if(ticket==null)
			return "Not found";
		indexAndQueryService.removeIndex(ticket);
		return "Slot number "+ticket.getAllottedSlot()+" is free";
		
	}

	@Override
	public IndexAndQueryService getIndexAndQueryService() {
		return indexAndQueryService;
	}
}
