package com.warpaint.input.model;

import java.util.List;

public class Input {

	private final Price lastPrice;

	private final List<Change> changes;

	public Input(Price lastPrice, List<Change> changes) {
		super();
		this.lastPrice = lastPrice;
		this.changes = changes;
	}

	public Price getLastPrice() {
		return lastPrice;
	}

	public List<Change> getChanges() {
		return changes;
	}

	@Override
	public String toString() {
		return "Input [lastPrice=" + lastPrice + ", changes=" + changes + "]";
	}

}
