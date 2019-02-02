package com.gramcha.parkinglot.service;

import java.util.List;

import com.gramcha.parkinglot.model.Ticket;

public interface IndexAndQueryService {
	//indexing operations
	void addIndex(Ticket ticket);
	void removeIndex(Ticket ticket);
	
	//search operations
	List<String> getCarRegistrationNumbers(String color);
	Integer getSlotNumberOfAllocatedCar(String registrationNumber);
	List<Integer> getSlotNumbersOfAllocatedCar(String color);
	
	//print status
	void printStatus(); 
}
