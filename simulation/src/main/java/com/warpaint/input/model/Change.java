package com.warpaint.input.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.warpaint.common.LocalDateFormatter;

public class Change {

	private final LocalDate month;

	private final double change;

	public Change(LocalDate month, double change) {
		this.month = month;
		this.change = change;
	}

	@JsonIgnore
	public LocalDate getMonth() {
		return month;
	}

	public String getFormattedDate() {
		return LocalDateFormatter.format(month);
	}

	public double getChange() {
		return change;
	}

	@Override
	public String toString() {
		return "Change [month=" + month + ", change=" + change + "]";
	}

}
