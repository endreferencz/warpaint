package com.warpaint.common;

public class RestResult {

	private final String result;

	private final double latency;

	public RestResult(String result, double latency) {
		this.result = result;
		this.latency = latency;
	}

	public String getResult() {
		return result;
	}

	public double getLatency() {
		return latency;
	}

}
