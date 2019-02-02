package com.gramcha.parkinglot.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;



import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.gramcha.parkinglot.model.Car;
import com.gramcha.parkinglot.model.ParkingLot;
import com.gramcha.parkinglot.model.Ticket;
import com.gramcha.parkinglot.service.impl.DefaultParkingLotService;

public class DefaultParkingLotServiceTest {
	private static ParkingLotService parkingLotService;
	static final int noOfParkingSlots = 10;
	ParkingLot parkingLotInstance;
	@BeforeClass
	public static void initParkingLotService() {
		parkingLotService = new DefaultParkingLotService();
	}
	@Before
	public void beforeEachTest() {
		parkingLotInstance  = parkingLotService.createParkingLot(noOfParkingSlots);
	}
	@After
	public void aeforeEachTest() {
		//as of now nothing
	}
	@Test
	public void createParkinglotForGivenNumberOfSlotsAndReturnParkingLotId() {
		ParkingLot newParkingLot  = parkingLotService.createParkingLot(noOfParkingSlots);
	    assertNotNull(newParkingLot);
	    assertEquals(noOfParkingSlots, newParkingLot.getNumberOfSlots());
	}
	
	@Test
	public void whenParkingLotCreatedItsFreeSlotsShouldBeEqualToCapacity() {
		assertEquals(parkingLotInstance.getNumberOfSlots(),parkingLotInstance.getNumberOfFreeSlots());
	}
	
	@Test
	public void whenParkingLotHaveFreeSlotsAllocateASlotToACar() {
		Car car = new Car("KA-01-HH-1234","White");
		Ticket ticket = parkingLotService.allocateSlot(car);
		assertEquals(car.getRegistrationNumber(),ticket.getRegistrationNumber());
		assertEquals(car.getColor(),ticket.getColor());
		assertEquals(true,ticket.isAlloted());
		System.out.println(ticket);
	}
	
	@Test
	public void allocateAllAvailableSlots() {
		for(int i=0;i<noOfParkingSlots;i++) {
			Car car = new Car("KA-01-HH-1234"+i,"White");
			Ticket ticket = parkingLotService.allocateSlot(car);
			assertEquals(car.getRegistrationNumber(),ticket.getRegistrationNumber());
			assertEquals(car.getColor(),ticket.getColor());
			assertEquals(true,ticket.isAlloted());
			System.out.println(ticket);
		}
	}
	
	@Test
	public void whenThereIsNoFreeSlotShouldReturnParkingFullMessage() {
		//first allocate all the slots
		for(int i=0;i<noOfParkingSlots;i++) {
			Car car = new Car("KA-01-HH-1234"+i,"White");
			Ticket ticket = parkingLotService.allocateSlot(car);
			System.out.println(ticket);
		}
		Car car = new Car("KA-01-HH-9999","White");
		Ticket ticket = parkingLotService.allocateSlot(car);
		assertEquals(false,ticket.isAlloted());
		assertEquals("Sorry, parking lot is full",ticket.toString());
		System.out.println(ticket);
	}
	@Test
	public void whenThereIsAFreeSlotNearToEntryAllocateThatToIncomingCar() {
		//TODO: to simulate this case we need to remove some car closer to entry. We can revisit this after completing deallocation of a slot.
		
	}
	
	@Test
	public void whenCarLeavesFromParkingLotThatSlotShouldBecomeFree() {
		Car car = new Car("KA-01-HH-9999","White");
		Ticket ticket = parkingLotService.allocateSlot(car);
		System.out.println(ticket);
		String message = parkingLotService.deallocateSlot(ticket.getAllottedSlot());
		assertNotNull(message);
		System.out.println(message);
	}
}
