package com.gramcha.parkinglot.service;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.gramcha.parkinglot.service.impl.CmdLineProcessorService;

public class CommandProcessorServiceTest {
	private static CommandProcessorService commandProcessorService;
	
	@BeforeClass
	public static void initParkingLotService() {
		commandProcessorService = new CmdLineProcessorService();
	}
	
	@Test
	public void WhenValidCommandForCreateParkingLotGivenThereShouldNotBeAnyException() {
		try {
			commandProcessorService.process("create_parking_lot 6");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
	}
	
	@Test(expected=Exception.class)
	public void WhenInValidCommandForCreateParkingLotGivenThereShouldBeAnException() throws Exception {
		commandProcessorService.process("create_parking_lot");
	}
	
	@Test
	public void WhenValidCommandForParkingACarGivenThereShouldNotBeAnyException() {
		try {
			commandProcessorService.process("park KA-01-HH-1234 White");
			commandProcessorService.process("park KA-01-HH-9999 White");
			commandProcessorService.process("park KA-01-BB-0001 Black");
			commandProcessorService.process("park KA-01-HH-7777 Red");
			commandProcessorService.process("park KA-01-HH-2701 Blue");
			commandProcessorService.process("park KA-01-HH-3141 Black");
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	@Test(expected=Exception.class)
	public void WhenInValidCommandForParkingACarGivenThereShouldBeAnException() throws Exception {
		commandProcessorService.process("park KA-01-HH-1234");
	}
	@Test
	public void WhenValidCommandForLeavingACarGivenThereShouldNotBeAnyException() {
		try {
			commandProcessorService.process("leave 4");
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	@Test(expected=Exception.class)
	public void WhenInValidCommandForLeavingACarGivenThereShouldBeAnException() throws Exception {
		commandProcessorService.process("leave");
	}
}
