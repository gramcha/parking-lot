package com.gramcha.parkinglot.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.gramcha.parkinglot.model.Car;
import com.gramcha.parkinglot.model.ParkingLot;
import com.gramcha.parkinglot.model.Ticket;
import com.gramcha.parkinglot.service.impl.DefaultParkingLotService;

/*
create_parking_lot 6
park KA-01-HH-1234 White
park KA-01-HH-9999 White
park KA-01-BB-0001 Black
park KA-01-HH-7777 Red
park KA-01-HH-2701 Blue
park KA-01-HH-3141 Black
leave 4
status
park KA-01-P-333 White
park DL-12-AA-9999 White
registration_numbers_for_cars_with_colour White
slot_numbers_for_cars_with_colour White
slot_number_for_registration_number KA-01-HH-3141
slot_number_for_registration_number MH-04-AY-1111
 * */

public class SampleTestCaseFromProblemStatement {
	private static ParkingLotService parkingLotService;
	private IndexAndQueryService indexAndQueryService;
	ParkingLot parkingLotInstance;
	static final int noOfParkingSlots = 6;
	@BeforeClass
	public static void initParkingLotService() {
		parkingLotService = new DefaultParkingLotService();
	}

	@Before
	public void beforeEachTest() {
		parkingLotInstance = parkingLotService.createParkingLot(noOfParkingSlots);
		indexAndQueryService = parkingLotService.getIndexAndQueryService();
	}

	@After
	public void aeforeEachTest() {
		// as of now nothing
	}
	@Test
	public void execute() {
//		park KA-01-HH-1234 White
//		park KA-01-HH-9999 White
//		park KA-01-BB-0001 Black
//		park KA-01-HH-7777 Red
//		park KA-01-HH-2701 Blue
//		park KA-01-HH-3141 Black
		
		Car car1 = new Car("KA-01-HH-1234", "White");
		Ticket ticket1 = parkingLotService.allocateSlot(car1);
		System.out.println(ticket1);
		
		Car car2 = new Car("KA-01-HH-9999", "White");
		Ticket ticket2 = parkingLotService.allocateSlot(car2);
		System.out.println(ticket2);
		
		Car car3 = new Car("KA-01-BB-0001", "Black");
		Ticket ticket3 = parkingLotService.allocateSlot(car3);
		System.out.println(ticket3);
		
		Car car4 = new Car("KA-01-HH-7777", "Red");
		Ticket ticket4 = parkingLotService.allocateSlot(car4);
		System.out.println(ticket4);
		
		Car car5 = new Car("KA-01-HH-2701", "Blue");
		Ticket ticket5 = parkingLotService.allocateSlot(car5);
		System.out.println(ticket5);
		
		Car car6 = new Car("KA-01-HH-3141", "Black");
		Ticket ticket6 = parkingLotService.allocateSlot(car6);
		System.out.println(ticket6);
		
//		leave 4
		
		String message = parkingLotService.deallocateSlot(ticket4.getAllottedSlot());
		assertNotNull(message);
		System.out.println(message);
		
//		park KA-01-P-333 White
//		park DL-12-AA-9999 White
		
		Car car7 = new Car("KA-01-P-333", "White");
		Ticket ticket7 = parkingLotService.allocateSlot(car7);
		System.out.println(ticket7);
		
		Car car8 = new Car("DL-12-AA-9999", "Black");
		Ticket ticket8 = parkingLotService.allocateSlot(car8);
		System.out.println(ticket8);
		
//		registration_numbers_for_cars_with_colour White
		List<String> registrationNumbers = indexAndQueryService.getCarRegistrationNumbers("White");
		//TODO: result should come with out boxes. currently list tostring adds the boxes [KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333]
		System.out.println(registrationNumbers);
		
//		slot_numbers_for_cars_with_colour White
		List<Integer> slotNumbers = indexAndQueryService.getSlotNumbersOfAllocatedCar("White");
		//TODO: result should come with out boxes. currently list tostring adds the boxes [1, 2, 4]
		System.out.println(slotNumbers);
		
//		slot_number_for_registration_number KA-01-HH-3141
		Integer slotNumber1 = indexAndQueryService.getSlotNumberOfAllocatedCar("KA-01-HH-3141");
		System.out.println(slotNumber1);
//		slot_number_for_registration_number MH-04-AY-1111
		Integer slotNumber2 = indexAndQueryService.getSlotNumberOfAllocatedCar("MH-04-AY-1111");
		//TODO: when null case comes we need to print "Not found" as per requirement. Handle it commandline and file executor.
		if(slotNumber2!=null)
			System.out.println(slotNumber2);
		else
			System.out.println("Not found");
		
		
		
	}
}
