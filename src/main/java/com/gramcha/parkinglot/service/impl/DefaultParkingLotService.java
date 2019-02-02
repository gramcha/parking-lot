package com.gramcha.parkinglot.service.impl;

import java.util.HashMap;
import java.util.Map;


import com.gramcha.parkinglot.model.ParkingLot;
import com.gramcha.parkinglot.service.ParkingLotService;

public class DefaultParkingLotService implements ParkingLotService{

	Map<String, ParkingLot> parkingLots = new HashMap<>();
	public ParkingLot createParkingLot(int noOfParkingSlots) {
		// TODO Auto-generated method stub
		ParkingLot newParkingLot = new ParkingLot(noOfParkingSlots);
		parkingLots.put(newParkingLot.getId(), newParkingLot);
		return newParkingLot;
	}

}
