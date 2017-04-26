package com.warpaint.simulation;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.warpaint.common.RestResult;
import com.warpaint.input.model.Price;
import com.warpaint.storage.StorageService;

@RestController
public class SimulationController {

	@Autowired
	private SimulationService simulationService;

	@Autowired
	private StorageService storageService;

	@RequestMapping("/simulate")
	public RestResult simulate() throws IOException {
		long start = System.currentTimeMillis();
		storageService.setResult(simulationService.simulate(storageService.getInput()));
		return new RestResult("ok", System.currentTimeMillis() - start);
	}

	@RequestMapping("/output")
	public List<Price> showOutput(@RequestParam("index") int index) throws IOException {
		List<Price> output = storageService.getResult().getSimulation(index);
		if (output == null) {
			throw new IllegalStateException("Please run the simulation first.");
		}
		return output;
	}

}
