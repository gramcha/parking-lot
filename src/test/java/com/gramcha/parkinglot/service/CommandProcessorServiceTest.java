package com.gramcha.parkinglot.service;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.gramcha.parkinglot.model.ParkingLot;
import com.gramcha.parkinglot.service.impl.CmdLineProcessorService;
import com.gramcha.parkinglot.service.impl.DefaultParkingLotService;

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
}
