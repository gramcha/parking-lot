package com.gramcha.parkinglot.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
