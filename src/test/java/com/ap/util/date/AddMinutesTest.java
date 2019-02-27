package com.ap.util.date;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddMinutesTest {

	private final static int VALID_OFFSET = 5;

	private final static String VALID_TIME = "1:15 PM";

	@Test(expected = IllegalArgumentException.class)
	public void testNullTime() {
		AddTimeUtil.addMinutes(null, VALID_OFFSET);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEmptyTime() {
		AddTimeUtil.addMinutes("", VALID_OFFSET);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidTime() {
		AddTimeUtil.addMinutes("ZZZ", VALID_OFFSET);
	}

	@Test
	public void testNoHourRolloverNoMeridiemRollover() {
		assertEquals("1:20 PM", AddTimeUtil.addMinutes(VALID_TIME, VALID_OFFSET));
	}

	@Test
	public void testHourRolloverNoMeridiemRollover() {
		assertEquals("2:20 PM", AddTimeUtil.addMinutes(VALID_TIME, VALID_OFFSET * 12));
	}

	@Test
	public void testNoHourRolloverMeridiemRollover() {
		assertEquals("1:15 AM", AddTimeUtil.addMinutes(VALID_TIME, VALID_OFFSET * 12 * 12));
	}

	@Test
	public void testHourRolloverMeridiemRollover() {
		assertEquals("2:15 AM", AddTimeUtil.addMinutes(VALID_TIME, VALID_OFFSET * 12 * 13));
	}
}
