package com.gramcha.parkinglot.service;

import com.gramcha.parkinglot.model.Car;
import com.gramcha.parkinglot.model.ParkingLot;
import com.gramcha.parkinglot.model.Ticket;

public interface ParkingLotService {
	ParkingLot createParkingLot(int noOfParkingSlots);

	Ticket allocateSlot(Car car);

	String deallocateSlot(int slotNumber);
	
	IndexAndQueryService getIndexAndQueryService();
}
