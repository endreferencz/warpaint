package com.warpaint.storage;

import org.springframework.stereotype.Service;

import com.warpaint.input.model.Input;
import com.warpaint.simulation.model.SimulationResult;

@Service
public class StorageService {

	private Input input;

	private SimulationResult result;

	public synchronized Input getInput() {
		return input;
	}

	public synchronized void setInput(Input input) {
		this.input = input;
	}

	public synchronized SimulationResult getResult() {
		return result;
	}

	public synchronized void setResult(SimulationResult result) {
		this.result = result;
	}

}
