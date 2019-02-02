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
	@BeforeClass
	public static void initParkingLotService() {
		parkingLotService = new DefaultParkingLotService();
	}
	@Before
	public void beforeEachTest() {
		//as of now nothing
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
}
