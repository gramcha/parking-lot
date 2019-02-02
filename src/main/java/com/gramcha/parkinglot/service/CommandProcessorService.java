package com.gramcha.parkinglot.service;

import com.gramcha.parkinglot.Constants;
import com.gramcha.parkinglot.service.impl.DefaultParkingLotService;

public abstract class CommandProcessorService {
	private ParkingLotService parkingLotService = new DefaultParkingLotService();
	

	public abstract void parse() throws Exception;

	public void process(String line) throws Exception {
//		IndexAndQueryService indexAndQueryService = parkingLotService.getIndexAndQueryService();
		
		String[] inputStrArr = line.split(" ");
		if (inputStrArr[0].equals("")) {
			System.out.println("Invalid command");
			return;
		}
		String commandStr = inputStrArr[0];
		switch (commandStr) {
		case Constants.CREATE_PARKING_LOT:
			processCreateParkingLotCommand(inputStrArr);
			break;
		case Constants.PARK:
			if(inputStrArr.length != 3) {
				throw new Exception("Invalid no of arguments for command : " + Constants.PARK);
			} 
			throw new Exception("no implementation");
			
		case Constants.LEAVE:
			break;
		case Constants.STATUS:
			break;
		case Constants.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR:
			break;
		case Constants.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR:
			break;
		case Constants.SLOT_NUMBER_FOR_REGISTRATION_NUMBER:
			break;
		default:
			System.out.println("Invalid command");
		}
	}

	private void processCreateParkingLotCommand(String[] inputStrArr) throws Exception {
		if(inputStrArr.length != 2) {
			throw new Exception("Invalid no of arguments for command : " + Constants.CREATE_PARKING_LOT);
		} 
		int noOfParkingSlots = Integer.parseInt(inputStrArr[1]);
		parkingLotService.createParkingLot(noOfParkingSlots);
	}
}
