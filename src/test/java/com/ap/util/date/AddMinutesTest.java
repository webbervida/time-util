package com.ap.util.date;

import org.junit.Test;

public class AddMinutesTest {

	private final static int VALID_OFFSET = 5;

	@Test(expected = IllegalArgumentException.class)
	public void testNullTime() {
		AddTimeUtil.addTime(null, VALID_OFFSET);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEmptyTime() {
		AddTimeUtil.addTime("", VALID_OFFSET);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidTime() {
		AddTimeUtil.addTime("ZZZ", VALID_OFFSET);
	}
}
