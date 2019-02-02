package com.gramcha.parkinglot.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
			colorBasedList.removeIf(t -> t.getRegistrationNumber().equals(ticket.getRegistrationNumber()));
		}
	}

	@Override
	public List<String> getCarRegistrationNumbers(String color) {
		List<Ticket> tickets = colorBasedIndex.get(color);
		if (tickets == null)
			return new ArrayList<String>();
		List<String> regNumbers = tickets.stream().map(t -> {
			return t.getRegistrationNumber();
		}).collect(Collectors.toList());
		return regNumbers;
	}

	@Override
	public Integer getSlotNumberOfAllocatedCar(String registrationNumber) {
		if (registrationNumberBasedIndex.containsKey(registrationNumber)) {
			Ticket ticket = registrationNumberBasedIndex.get(registrationNumber);
			return ticket.getAllottedSlot();
		}
		return null;
	}

	@Override
	public List<Integer> getSlotNumbersOfAllocatedCar(String color) {
		List<Ticket> tickets = colorBasedIndex.get(color);
		if (tickets == null)
			return new ArrayList<Integer>();
		List<Integer> slotNumbers = tickets.stream().map(t -> {
			return t.getAllottedSlot();
		}).collect(Collectors.toList());
		return slotNumbers;
	}

	@Override
	public void printStatus() {
		String header = "Slot No.\tRegistration No\tColour";
		System.out.println(header);
		Comparator<Entry<String, Ticket>> valueComparator = getValueComparator();
		Map<String, Ticket> result = registrationNumberBasedIndex.entrySet()
										.stream()
										.sorted(valueComparator)
										.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
		StringBuilder builder = new StringBuilder();
		result.forEach((key, value) -> {
			builder.append(value.getAllottedSlot());
			builder.append("\t");
			builder.append(value.getRegistrationNumber());
			builder.append("\t");
			builder.append(value.getColor());
			builder.append("\n");
		});
		if (builder.length() > 0) {
			builder.deleteCharAt(builder.length() - 1);// removing unwanted newline
			System.out.println(builder.toString());
		}
	}

	private Comparator<Entry<String, Ticket>> getValueComparator() {
		return new Comparator<Entry<String, Ticket>>() {
			@Override
			public int compare(Entry<String, Ticket> e1, Entry<String, Ticket> e2) {
				Ticket v1 = e1.getValue();
				Ticket v2 = e2.getValue();
				return v1.compareTo(v2);
			}
		};
	}

}
