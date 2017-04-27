package com.warpaint;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import com.warpaint.input.FetchInputService;
import com.warpaint.input.model.Input;
import com.warpaint.simulation.MultithreadSimulationService;
import com.warpaint.simulation.SimpleSimulationService;
import com.warpaint.simulation.model.SimulationResult;

@State(Scope.Thread)
public class SimulationBenchmark {

	private Input input;

	public SimulationBenchmark() {
		FetchInputService fetchInputService = new FetchInputService();
		try {
			input = fetchInputService.fetchInput();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Benchmark
	public SimulationResult testSingleThread() {
		return new SimpleSimulationService().simulate(input);
	}

	@Benchmark
	public SimulationResult testMultiThread() {
		return new MultithreadSimulationService().simulate(input);
	}

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder().include(SimulationBenchmark.class.getSimpleName()).warmupIterations(5)
				.measurementIterations(5).threads(4).forks(1).timeUnit(TimeUnit.MILLISECONDS).mode(Mode.AverageTime)
				.build();
		new Runner(opt).run();
	}

}
