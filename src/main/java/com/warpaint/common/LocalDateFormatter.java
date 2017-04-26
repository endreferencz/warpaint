package com.warpaint.common;

import java.time.LocalDate;

public class LocalDateFormatter {
	
	public static String format(LocalDate date) {
		return date.getYear() + "." + date.getMonth() + "." + date.getDayOfMonth();
	}

}
