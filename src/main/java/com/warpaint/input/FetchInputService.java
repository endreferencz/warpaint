package com.warpaint.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.warpaint.input.model.Change;
import com.warpaint.input.model.Input;
import com.warpaint.input.model.Price;

@Service
public class FetchInputService {

	private static final String CSV_SEPARATOR = ",";
	public static final int RESULT_SIZE = 240;
	private static final String DOWNLOAD_URL = "https://chart.finance.yahoo.com/table.csv?s=SPY&a=0&b=29&c=1993&d=3&e=26&f=2017&g=d&ignore=.csv";

	public Input fetchInput() throws IOException {
		try (InputStream stream = new URL(DOWNLOAD_URL).openStream()) {
			Iterator<Price> elements = createPriceElementIterator(stream);
			return processElements(elements);
		}
	}

	private Input processElements(Iterator<Price> elements) {
		Price nextDayPrice = null;
		Price nextMonthPrice = null;
		Price firstPrice = null;
		List<Change> changes = new ArrayList<>(RESULT_SIZE);
		while (elements.hasNext() && changes.size() < RESULT_SIZE) {
			Price price = elements.next();
			if (nextDayPrice != null && nextDayPrice.getMonth() != price.getMonth()) {
				if (firstPrice == null) {
					firstPrice = nextDayPrice;
				}
				if (nextMonthPrice != null) {
					Change change = new Change(nextMonthPrice.getDate(), calculateRatio(nextMonthPrice, nextDayPrice));
					changes.add(change);
				}
				nextMonthPrice = nextDayPrice;
			}
			nextDayPrice = price;
		}
		if (firstPrice == null) {
			throw new IllegalStateException("No input data!");
		}
		return new Input(firstPrice, changes);
	}

	private double calculateRatio(Price nextMonthPrice, Price currentMonthPrice) {
		return nextMonthPrice.getOpen() / currentMonthPrice.getOpen();
	}

	private Iterator<Price> createPriceElementIterator(InputStream stream) {
		InputStreamReader streamReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
		BufferedReader reader = new BufferedReader(streamReader);
		return reader.lines().skip(1).map(line -> line.split(CSV_SEPARATOR))
				.map(items -> new Price(Double.valueOf(items[1]), LocalDate.parse(items[0]))).iterator();
	}

}
