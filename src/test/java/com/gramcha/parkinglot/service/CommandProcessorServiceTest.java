package com.gramcha.parkinglot.service;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.gramcha.parkinglot.service.impl.CmdLineProcessorService;
import com.gramcha.parkinglot.service.impl.FileProcessorService;

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
			System.out.println(e);
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
			commandProcessorService.process("park KA-01-P-333 White");
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	@Test(expected=Exception.class)
	public void WhenInValidCommandForParkingACarGivenThereShouldBeAnException() throws Exception {
		commandProcessorService.process("park KA-01-HH-1234");
	}
	@Test
	public void WhenValidLeavingACarCommandGivenThereShouldNotBeAnyException() {
		try {
			commandProcessorService.process("leave 4");
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	@Test(expected=Exception.class)
	public void WhenInValidLeavingACarCommandGivenThereShouldBeAnException() throws Exception {
		commandProcessorService.process("leave");
	}
	@Test
	public void WhenValidStatusCommandGivenThereShouldNotBeAnyException() {
		try {
			commandProcessorService.process("status");
		} catch (Exception e) {
			System.out.println(e);
			assertTrue(false);
		}
	}
	@Test(expected=Exception.class)
	public void WhenInValidStatusCommandGivenThereShouldBeAnException() throws Exception {
		commandProcessorService.process("status 12");
	}
	@Test
	public void WhenValidRegistration_numbers_for_cars_with_colourCommandGivenThereShouldNotBeAnyException() {
		try {
			commandProcessorService.process("registration_numbers_for_cars_with_colour White");
		} catch (Exception e) {
			System.out.println(e);
			assertTrue(false);
		}
	}
	@Test(expected=Exception.class)
	public void WhenInValidRegistration_numbers_for_cars_with_colourCommandGivenThereShouldBeAnException() throws Exception {
		commandProcessorService.process("registration_numbers_for_cars_with_colour White 12");
	}
	@Test
	public void WhenValidSlot_numbers_for_cars_with_colourCommandGivenThereShouldNotBeAnyException() {
		try {
			commandProcessorService.process("slot_numbers_for_cars_with_colour White");
		} catch (Exception e) {
			System.out.println(e);
			assertTrue(false);
		}
	}
	@Test(expected=Exception.class)
	public void WhenInValidSlot_numbers_for_cars_with_colourCommandGivenThereShouldBeAnException() throws Exception {
		commandProcessorService.process("slot_numbers_for_cars_with_colour White 12");
	}
	@Test
	public void WhenValidSlot_number_for_registration_numberCommandGivenThereShouldNotBeAnyException() {
		try {
			commandProcessorService.process("slot_number_for_registration_number KA-01-HH-3141");
		} catch (Exception e) {
			System.out.println(e);
			assertTrue(false);
		}
	}
	@Test(expected=Exception.class)
	public void WhenInValidSlot_number_for_registration_numberCommandGivenThereShouldBeAnException() throws Exception {
		commandProcessorService.process("slot_number_for_registration_number KA-01-HH-3141 23");
	}
	
	@Test
	public void WhenEmptyCommandPassedThereShouldBeNoException() throws Exception {
		commandProcessorService.process("");
	}
	@Test
	public void WhenInvalidCommandPassedThereShouldBeNoException() throws Exception {
		commandProcessorService.process("RAIL");
	}
	@Test(expected=Exception.class)
	public void WhenStatusCommandExecutedBeforeParkingLotCreationThereShouldBeAnException() throws Exception {
		CommandProcessorService tempService = new CmdLineProcessorService();
		tempService.process("status");
	}
	@Test(expected=Exception.class)
	public void WhenRegistration_numbers_for_cars_with_colourCommandExecutedBeforeParkingLotCreationThereShouldBeAnException() throws Exception {
		CommandProcessorService tempService = new CmdLineProcessorService();
		tempService.process("registration_numbers_for_cars_with_colour White");
	}
	@Test(expected=Exception.class)
	public void WhenSlot_numbers_for_cars_with_colourCommandExecutedBeforeParkingLotCreationThereShouldBeAnException() throws Exception {
		CommandProcessorService tempService = new CmdLineProcessorService();
		tempService.process("slot_numbers_for_cars_with_colour White");
	}
	@Test(expected=Exception.class)
	public void WhenSlot_number_for_registration_numberCommandExecutedBeforeParkingLotCreationThereShouldBeAnException() throws Exception {
		CommandProcessorService tempService = new CmdLineProcessorService();
		tempService.process("slot_number_for_registration_number KA-01-HH-3141");
	}
	@Test
	public void WhenFileProcessorServiceUsedThereShouldNotBeAnyException() throws Exception {
		CommandProcessorService tempService = new FileProcessorService("file_input.txt");
		tempService.parse();
	}
}
