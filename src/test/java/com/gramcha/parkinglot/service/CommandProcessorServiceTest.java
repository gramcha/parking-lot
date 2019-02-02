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
	public void WhenValidCommandForCreateParkingLotGivenThereShouldNotbeAnyException() {
		try {
			commandProcessorService.process("create_parking_lot 6");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
	}
	
	@Test(expected=Exception.class)
	public void WhenInValidCommandForCreateParkingLotGivenThereShouldNotbeAnyException() throws Exception {
		commandProcessorService.process("create_parking_lot");
	}
	
	@Test
	public void WhenValidCommandForParkingACarGivenThereShouldNotbeAnyException() {
		try {
			commandProcessorService.process("park KA-01-HH-1234 White");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
	}
}
