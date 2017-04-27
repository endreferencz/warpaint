package com.warpaint.simulation.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.warpaint.input.model.Price;

public class SimulationResult {

	private final LocalDate startDate;
	private final List<double[]> simulations;

	public SimulationResult(LocalDate startDate, List<double[]> simulations) {
		this.startDate = startDate;
		this.simulations = simulations;
	}

	public List<Price> getSimulation(int index) {
		LocalDate currentDate = startDate;
		double[] simulation = simulations.get(index);
		List<Price> prices = new ArrayList<>(simulation.length);
		for (double price : simulation) {
			prices.add(new Price(price, currentDate));
			currentDate = currentDate.plusMonths(1);
			currentDate = currentDate.withDayOfMonth(1);
		}
		return prices;
	}

}
