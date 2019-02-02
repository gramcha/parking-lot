package com.gramcha.parkinglot.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.gramcha.parkinglot.model.Car;
import com.gramcha.parkinglot.model.ParkingLot;
import com.gramcha.parkinglot.model.Ticket;
import com.gramcha.parkinglot.service.impl.DefaultParkingLotService;

public class DefaultIndexAndQueryServiceTest {
	private static ParkingLotService parkingLotService;
	static final int noOfParkingSlots = 10;
	private ParkingLot parkingLotInstance;
	private IndexAndQueryService indexAndQueryService;

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
	public void WhenSlotIsAllocatedQueryServiceShouldReturnCarRegistrationNumbersForGivenColor() {
		Car car = new Car("KA-01-HH-1234", "White");
		Ticket ticket = parkingLotService.allocateSlot(car);
		System.out.println(ticket);
		Car car2 = new Car("KA-01-HH-9999", "White");
		ticket = parkingLotService.allocateSlot(car2);
		System.out.println(ticket);
		Car car3 = new Car("KA-01-HH-3141", "Black");
		ticket = parkingLotService.allocateSlot(car3);
		System.out.println(ticket);
		List<String> registrationNumbers = indexAndQueryService.getCarRegistrationNumbers(car.getColor());
		assertTrue(registrationNumbers.contains(car.getRegistrationNumber()));
		assertTrue(registrationNumbers.contains(car2.getRegistrationNumber()));
		assertEquals(2, registrationNumbers.size());
		System.out.println(registrationNumbers);
	}
	
	@Test
	public void WhenSlotIsAllocatedCarsColorNotHavingGivenColorThenQueryServiceShouldReturnEmptyList() {
		Car car = new Car("KA-01-HH-1234", "White");
		Ticket ticket = parkingLotService.allocateSlot(car);
		System.out.println(ticket);
		List<String> registrationNumbers = indexAndQueryService.getCarRegistrationNumbers("Black");
		assertEquals(0, registrationNumbers.size());
	}
	
	@Test
	public void WhenSlotAllocatedQueryServiceShouldReturnTheSlotNumberForGivenRegistrationNumber() {
		Car car = new Car("KA-01-HH-1234", "White");
		Ticket ticket = parkingLotService.allocateSlot(car);
		System.out.println(ticket);
		Integer slotNumber = indexAndQueryService.getSlotNumberOfAllocatedCar(car.getRegistrationNumber());
		assertEquals((Integer)ticket.getAllottedSlot(),slotNumber);
	}
	@Test
	public void WhenGivenCarNotAllocatedQueryServiceShouldReturnTheNullForGivenRegistrationNumber() {
		Integer slotNumber = indexAndQueryService.getSlotNumberOfAllocatedCar("KA-01-HH-1234");
		assertEquals(null,slotNumber);
	}
	@Test
	public void WhenSlotIsAllocatedQueryServiceShouldReturnCarSlotNumbersForGivenColor() {
		Car car = new Car("KA-01-HH-1234", "White");
		Ticket ticket = parkingLotService.allocateSlot(car);
		System.out.println(ticket);
		Car car2 = new Car("KA-01-HH-9999", "White");
		Ticket ticket2 = parkingLotService.allocateSlot(car2);
		System.out.println(ticket2);
		Car car3 = new Car("KA-01-HH-3141", "Black");
		Ticket ticket3 = parkingLotService.allocateSlot(car3);
		System.out.println(ticket3);
		List<Integer> slotNumbers = indexAndQueryService.getSlotNumbersOfAllocatedCar(car.getColor());
		assertTrue(slotNumbers.contains(ticket.getAllottedSlot()));
		assertTrue(slotNumbers.contains(ticket2.getAllottedSlot()));
	}
}
