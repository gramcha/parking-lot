package com.gramcha.parkinglot.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.gramcha.parkinglot.model.Ticket;
import com.gramcha.parkinglot.service.IndexAndQueryService;

public class DefaultIndexAndQueryService implements IndexAndQueryService {

	Map<String, List<Ticket>> colorBasedIndex = new HashMap<>();
	Map<String, Ticket> registrationNumberBasedIndex = new HashMap<>();

	@Override
	public void addIndex(Ticket ticket) {
		List<Ticket> colorBasedList = null;
		if (colorBasedIndex.containsKey(ticket.getColor())) {
			colorBasedList = colorBasedIndex.get(ticket.getColor());
		} else {
			colorBasedList = new ArrayList<>();
			colorBasedIndex.put(ticket.getColor(), colorBasedList);
		}
		colorBasedList.add(ticket);
		registrationNumberBasedIndex.put(ticket.getRegistrationNumber(), ticket);
	}

	@Override
	public void removeIndex(Ticket ticket) {
		registrationNumberBasedIndex.remove(ticket.getRegistrationNumber());
		if (colorBasedIndex.containsKey(ticket.getColor())) {
			List<Ticket> colorBasedList = colorBasedIndex.get(ticket.getColor());
			colorBasedList.removeIf(t->t.getRegistrationNumber().equals(ticket.getRegistrationNumber()));
		}
	}

	@Override
	public List<String> getCarRegistrationNumbers(String color) {
		List<Ticket> tickets = colorBasedIndex.get(color);
		if(tickets==null)
			return new ArrayList<String>();
		System.out.println(tickets.size());
		List<String> regNumbers = tickets.stream().map(t->{return t.getRegistrationNumber();}).collect(Collectors.toList());
		return regNumbers;
	}

	@Override
	public Integer getSlotNumberOfAllocatedCar(String registrationNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
