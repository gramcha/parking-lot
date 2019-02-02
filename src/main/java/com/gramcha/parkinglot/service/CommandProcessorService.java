package com.gramcha.parkinglot.service;

import com.gramcha.parkinglot.Constants;

public abstract class CommandProcessorService {
	public abstract void parse() throws Exception;

	public void process(String line) throws Exception {
		String[] inputStrArr = line.split(" ");
		if (inputStrArr[0].equals("")) {
			System.out.println("Invalid command");
			return;
		}
		String commandStr = inputStrArr[0];
		switch(commandStr) {
		case Constants.CREATE_PARKING_LOT:
			throw new Exception("no implementation");
			
		case Constants.PARK:
			break;
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
}
