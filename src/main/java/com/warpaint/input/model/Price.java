package com.warpaint.input.model;

import java.time.LocalDate;
import java.time.Month;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.warpaint.common.LocalDateFormatter;

public class Price {

	private final double open;

	private final LocalDate date;

	public Price(double open, LocalDate date) {
		this.open = open;
		this.date = date;
	}

	public double getOpen() {
		return open;
	}

	@JsonIgnore
	public LocalDate getDate() {
		return date;
	}

	public String getFormattedDate() {
		return LocalDateFormatter.format(date);
	}

	@JsonIgnore
	public Month getMonth() {
		return date.getMonth();
	}

	@Override
	public String toString() {
		return "Price [date=" + date + ", open=" + open + "]";
	}

}
