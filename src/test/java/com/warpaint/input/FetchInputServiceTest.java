package com.warpaint.input;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import com.warpaint.input.model.Input;
import com.warpaint.input.model.Price;

public class FetchInputServiceTest {

	private FetchInputService underTest = new FetchInputService();

	@Test
	public void testProcessElementsWithThreeMonths() {
		List<Price> input = Arrays.asList(new Price(3, LocalDate.of(2017, 3, 2)),
				new Price(2, LocalDate.of(2017, 3, 1)), new Price(1, LocalDate.of(2017, 2, 18)),
				new Price(1, LocalDate.of(2017, 1, 7)));
		Input result = underTest.processElements(input.iterator());
		Assert.assertEquals(result.getChanges().size(), 1);
		Assert.assertEquals(result.getChanges().get(0).getChange(), 2.0, 0.001);
		Assert.assertEquals(result.getChanges().get(0).getMonth(), LocalDate.of(2017, 3, 1));
		Assert.assertEquals(result.getLastPrice().getDate(), LocalDate.of(2017, 3, 1));
		Assert.assertEquals(result.getLastPrice().getOpen(), 2.0, 0.001);
	}

	@Test(timeout = 1000)
	public void testProcessElementsWithHugeInput() {
		List<Price> input = new ArrayList<>();
		LocalDate date = LocalDate.now();
		Random random = new Random();
		for (int i = 0; i < 100000; i++) {
			input.add(new Price(Math.random(), date));
			date = date.minusDays(random.nextInt(10));
		}
		Input result = underTest.processElements(input.iterator());
		Assert.assertEquals(result.getChanges().size(), 240);
	}

}
