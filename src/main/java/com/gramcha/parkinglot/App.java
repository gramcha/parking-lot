package com.gramcha.parkinglot;

import com.gramcha.parkinglot.service.CommandProcessorService;
import com.gramcha.parkinglot.service.impl.CmdLineProcessorService;
import com.gramcha.parkinglot.service.impl.FileProcessorService;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		CommandProcessorService cmdService;
		if (args.length > 0) {
			cmdService = new FileProcessorService(args[0]);
		} else {
			cmdService = new CmdLineProcessorService();
		}
		cmdService.parse();
	}
}
