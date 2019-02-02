package com.gramcha.parkinglot.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.gramcha.parkinglot.service.CommandProcessorService;

public class FileProcessorService extends CommandProcessorService {

	String filePath = null;

	public FileProcessorService(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void parse() throws Exception {
		File inputFile = new File(filePath);
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		String line;
		while ((line = br.readLine()) != null) {
			process(line);
		}
	}
}
