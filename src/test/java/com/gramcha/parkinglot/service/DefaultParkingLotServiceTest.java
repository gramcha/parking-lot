package com.gramcha.parkinglot.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;



import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.gramcha.parkinglot.model.ParkingLot;
import com.gramcha.parkinglot.service.impl.DefaultParkingLotService;

public class DefaultParkingLotServiceTest {
	private static ParkingLotService parkingLotService;
	ParkingLot parkingLotInstance;
	@BeforeClass
	public static void initParkingLotService() {
		parkingLotService = new DefaultParkingLotService();
	}
	@Before
	public void beforeEachTest() {
		int noOfParkingSlots = 10;
		parkingLotInstance  = parkingLotService.createParkingLot(noOfParkingSlots);
	}
	@After
	public void aeforeEachTest() {
		//as of now nothing
	}
	@Test
	public void createParkinglotForGivenNumberOfSlotsAndReturnParkingLotId() {
		int noOfParkingSlots = 10;
		ParkingLot newParkingLot  = parkingLotService.createParkingLot(noOfParkingSlots);
	    assertNotNull(newParkingLot);
	    assertEquals(noOfParkingSlots, newParkingLot.getNumberOfSlots());
	}
	
	@Test
	public void whenParkingLotCreatedItsFreeSlotsShouldBeEqualToCapacity() {
		assertEquals(parkingLotInstance.getNumberOfSlots(),parkingLotInstance.getNumberOfFreeSlots());
	}
}
