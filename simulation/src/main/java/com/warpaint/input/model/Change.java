package com.warpaint.input.model;

import java.text.NumberFormat;
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

	@JsonIgnore
	public double getChange() {
		return change;
	}

	public String getFormattedChange() {
		NumberFormat percentFormat = NumberFormat.getPercentInstance();
		percentFormat.setMinimumFractionDigits(1);
		return percentFormat.format(change - 1);
	}

	@Override
	public String toString() {
		return "Change [month=" + month + ", change=" + change + "]";
	}

}
