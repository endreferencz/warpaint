package com.warpaint.simulation;

import com.warpaint.input.model.Input;
import com.warpaint.simulation.model.SimulationResult;

public interface SimulationService {

	SimulationResult simulate(Input input);

}