package com.gramcha.parkinglot.service;

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
	private static IndexAndQueryService indexAndQueryService;
	static final int noOfParkingSlots = 10;
	ParkingLot parkingLotInstance;

	@BeforeClass
	public static void initParkingLotService() {
		parkingLotService = new DefaultParkingLotService();
		indexAndQueryService = parkingLotService.getIndexAndQueryService();
	}

	@Before
	public void beforeEachTest() {
		parkingLotInstance = parkingLotService.createParkingLot(noOfParkingSlots);
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
		List<String> registrationNumbers = indexAndQueryService.getCarRegistrationNumbers(car.getColor());
		assertTrue(registrationNumbers.contains(car.getRegistrationNumber()));
	}
}
