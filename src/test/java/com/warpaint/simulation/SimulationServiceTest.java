package com.warpaint.simulation;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.Test;

import com.warpaint.input.model.Change;
import com.warpaint.input.model.Input;
import com.warpaint.input.model.Price;
import com.warpaint.simulation.model.SimulationResult;

public class SimulationServiceTest {

	private class TestConstantSimulationService extends SimpleSimulationService {
		@Override
		protected Change randomChange(Input input) {
			return new Change(LocalDate.now(), 1.1);
		}
	}

	private class TestChangingSimulationService extends SimpleSimulationService {
		private AtomicInteger count = new AtomicInteger();

		@Override
		protected Change randomChange(Input input) {
			int value = count.incrementAndGet();
			return new Change(LocalDate.now(), value % 2 == 0 ? 2 : 0.5);
		}
	}

	@Test(timeout = 10000)
	public void testConstantSimulation() {
		SimulationService underTest = new TestConstantSimulationService();
		Input input = new Input(new Price(1, LocalDate.now()), Arrays.asList());
		SimulationResult result = underTest.simulate(input);
		Assert.assertEquals(Math.pow(1.1, 239), result.getSimulation(45).get(239).getOpen(), 0.001);
	}

	@Test(timeout = 10000)
	public void testChangingSimulation() {
		SimulationService underTest = new TestChangingSimulationService();
		Input input = new Input(new Price(1, LocalDate.now()), Arrays.asList());
		SimulationResult result = underTest.simulate(input);
		Assert.assertEquals(0.5, result.getSimulation(564).get(239).getOpen(), 0.001);
	}

}
