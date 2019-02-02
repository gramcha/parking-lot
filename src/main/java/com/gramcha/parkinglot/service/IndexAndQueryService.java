package com.gramcha.parkinglot.service;

import java.util.List;

import com.gramcha.parkinglot.model.Ticket;

public interface IndexAndQueryService {
	//indexing operations
	void addIndex(Ticket ticket);
	void removeIndex(Ticket ticket);
}
