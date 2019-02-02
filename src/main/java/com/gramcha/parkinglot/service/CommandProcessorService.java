package com.gramcha.parkinglot.service;

import static org.junit.Assert.assertNotNull;

import java.util.Iterator;
import java.util.List;

import com.gramcha.parkinglot.Constants;
import com.gramcha.parkinglot.model.Car;
import com.gramcha.parkinglot.model.Ticket;
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
			processParkCommand(inputStrArr);
			break;
		case Constants.LEAVE:
			processLeaveCommand(inputStrArr);
			break;
		case Constants.STATUS:
			processStatusCommand(inputStrArr);
			break;
		case Constants.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR:
			processRegistrationNumbersForCarsWithColourCommand(inputStrArr);
			break;
		case Constants.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR:
			processSlotNumbersForCarsWithColourCommand(inputStrArr);
			break;
		case Constants.SLOT_NUMBER_FOR_REGISTRATION_NUMBER:
			processSlotNumberForRegistrationNumberCommand(inputStrArr);
			break;
		default:
			System.out.println("Invalid command");
		}
	}

	private void processSlotNumberForRegistrationNumberCommand(String[] inputStrArr) throws Exception {
		if(inputStrArr.length != 2) {
			throw new Exception("Invalid no of arguments for command : " + Constants.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR);
		}
		IndexAndQueryService indexAndQueryService = parkingLotService.getIndexAndQueryService();
		Integer slotNumber= indexAndQueryService.getSlotNumberOfAllocatedCar(inputStrArr[1]);
		if(slotNumber!=null) {
			System.out.println(slotNumber);
		} else {
			System.out.println("Not found");
		}
	}

	private void processSlotNumbersForCarsWithColourCommand(String[] inputStrArr) throws Exception {
		if(inputStrArr.length != 2) {
			throw new Exception("Invalid no of arguments for command : " + Constants.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR);
		}
		IndexAndQueryService indexAndQueryService = parkingLotService.getIndexAndQueryService();
		List<Integer> slotNumbers = indexAndQueryService.getSlotNumbersOfAllocatedCar(inputStrArr[1]);
		if(slotNumbers.isEmpty()) {
			System.out.println("Not found");
		} else {
			Iterator<Integer> itr = slotNumbers.iterator();
			StringBuilder builder = new StringBuilder();
			while(itr.hasNext()) {
				builder.append(itr.next());
				if(itr.hasNext())
					builder.append(", ");
			}
			System.out.println(builder.toString());
		}
	}

	private void processRegistrationNumbersForCarsWithColourCommand(String[] inputStrArr) throws Exception {
		if(inputStrArr.length != 2) {
			throw new Exception("Invalid no of arguments for command : " + Constants.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR);
		}
		IndexAndQueryService indexAndQueryService = parkingLotService.getIndexAndQueryService();
		List<String> registrationNumbers = indexAndQueryService.getCarRegistrationNumbers(inputStrArr[1]);
		if(registrationNumbers.isEmpty()) {
			System.out.println("Not found");
		} else {
			Iterator<String> itr = registrationNumbers.iterator();
			StringBuilder builder = new StringBuilder();
			while(itr.hasNext()) {
				builder.append(itr.next());
				if(itr.hasNext())
					builder.append(", ");
			}
			System.out.println(builder.toString());
		}
	}

	private void processStatusCommand(String[] inputStrArr) throws Exception {
		if(inputStrArr.length != 1) {
			throw new Exception("Invalid no of arguments for command : " + Constants.STATUS);
		}
		IndexAndQueryService indexAndQueryService = parkingLotService.getIndexAndQueryService();
		indexAndQueryService.printStatus();
	}

	private void processLeaveCommand(String[] inputStrArr) throws Exception {
		if(inputStrArr.length != 2) {
			throw new Exception("Invalid no of arguments for command : " + Constants.LEAVE);
		}
		int allottedSlotNumber = Integer.parseInt(inputStrArr[1]);
		String message = parkingLotService.deallocateSlot(allottedSlotNumber);
		System.out.println(message);
	}

	private void processParkCommand(String[] inputStrArr) throws Exception {
		if(inputStrArr.length != 3) {
			throw new Exception("Invalid no of arguments for command : " + Constants.PARK);
		} 
		Car car = new Car(inputStrArr[1], inputStrArr[2]);
		Ticket ticket = parkingLotService.allocateSlot(car);
		System.out.println(ticket);
	}

	private void processCreateParkingLotCommand(String[] inputStrArr) throws Exception {
		if(inputStrArr.length != 2) {
			throw new Exception("Invalid no of arguments for command : " + Constants.CREATE_PARKING_LOT);
		} 
		int noOfParkingSlots = Integer.parseInt(inputStrArr[1]);
		parkingLotService.createParkingLot(noOfParkingSlots);
	}
}
