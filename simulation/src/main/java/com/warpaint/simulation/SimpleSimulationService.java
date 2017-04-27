package com.warpaint.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.warpaint.input.FetchInputService;
import com.warpaint.input.model.Change;
import com.warpaint.input.model.Input;
import com.warpaint.input.model.Price;
import com.warpaint.simulation.model.SimulationResult;

@Service
public class SimpleSimulationService implements SimulationService {

	private static final int NUMBER_OF_ITERATIONS = 10000;

	@Override
	public SimulationResult simulate(Input input) {
		List<double[]> simulations = new ArrayList<>(NUMBER_OF_ITERATIONS);
		Price lastPrice = input.getLastPrice();
		for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
			double[] prices = simulateScenario(input);
			simulations.add(prices);
		}
		return new SimulationResult(lastPrice.getDate(), simulations);
	}

	private double[] simulateScenario(Input input) {
		Price lastPrice = input.getLastPrice();
		double[] prices = new double[FetchInputService.RESULT_SIZE];
		prices[0] = lastPrice.getOpen();
		for (int j = 1; j < FetchInputService.RESULT_SIZE; j++) {
			prices[j] = prices[j - 1] * randomChange(input).getChange();
		}
		return prices;
	}

	protected Change randomChange(Input input) {
		List<Change> changes = input.getChanges();
		ThreadLocalRandom random = ThreadLocalRandom.current();
		int index = random.nextInt(changes.size());
		return changes.get(index);
	}

}
