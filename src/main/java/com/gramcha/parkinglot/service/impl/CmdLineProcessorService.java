package com.gramcha.parkinglot.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.gramcha.parkinglot.service.CommandProcessorService;

public class CmdLineProcessorService extends CommandProcessorService {

	@Override
	public void parse()  throws Exception {

		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String inputString = null;
			inputString = bufferRead.readLine();
			process(inputString);
		}
	}

}
