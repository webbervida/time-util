package com.ap.util.date;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AddMinutesTest {

	private final static int FIVE_MINUTES = 5;

	private final static String VALID_TIME = "1:15 PM";

	@Test(expected = IllegalArgumentException.class)
	public void testNullTime() {
		AddTimeUtil.addMinutes(null, FIVE_MINUTES);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEmptyTime() {
		AddTimeUtil.addMinutes("", FIVE_MINUTES);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidTime() {
		AddTimeUtil.addMinutes("ZZZ", FIVE_MINUTES);
	}

	@Test
	public void testNoWork() {
		assertEquals(VALID_TIME, AddTimeUtil.addMinutes(VALID_TIME, 0));
	}

	@Test
	public void testNoHourRolloverNoMeridiemRollover() {
		assertEquals("1:20 PM", AddTimeUtil.addMinutes(VALID_TIME, FIVE_MINUTES));
	}

	@Test
	public void testNoHourRolloverNoMeridiemRolloverNegativeTime() {
		assertEquals("1:10 PM", AddTimeUtil.addMinutes(VALID_TIME, FIVE_MINUTES * -1));
	}

	@Test
	public void testHourRolloverNoMeridiemRollover() {
		assertEquals("2:15 PM", AddTimeUtil.addMinutes(VALID_TIME, FIVE_MINUTES * 12));
	}

	@Test
	public void testNegativeHourRolloverNoMeridiemRollover() {
		assertEquals("12:15 PM", AddTimeUtil.addMinutes(VALID_TIME, -60));
	}

	@Test
	public void testMeridiemRollover() {
		assertEquals("11:59 AM", AddTimeUtil.addMinutes(VALID_TIME, -76));
	}

	@Test
	public void testNoHourRolloverMeridiemRollover() {
		assertEquals("1:15 AM", AddTimeUtil.addMinutes(VALID_TIME, FIVE_MINUTES * 12 * 12));
	}

	@Test
	public void testYesterdayRollover() {
		assertEquals(VALID_TIME, AddTimeUtil.addMinutes(VALID_TIME, (FIVE_MINUTES * 12 * 24) * -1));
	}

	@Test
	public void testYesterdayMeridiemRollover() {
		assertEquals("1:15 AM", AddTimeUtil.addMinutes(VALID_TIME, (FIVE_MINUTES * 12 * 12) * -1));
	}

	@Test
	public void testNoHourRolloverNegativeMeridiemRollover() {
		assertEquals("1:15 AM", AddTimeUtil.addMinutes(VALID_TIME, FIVE_MINUTES * 12 * -12));
	}

	@Test
	public void testHourRolloverMeridiemRollover() {
		assertEquals("2:15 AM", AddTimeUtil.addMinutes(VALID_TIME, FIVE_MINUTES * 12 * 13));
	}

	@Test
	public void testMidnight() {
		assertEquals("12:05 AM", AddTimeUtil.addMinutes("12:00 AM", FIVE_MINUTES));
	}

	@Test
	public void testMidnightNoAdd() {
		assertEquals("12:00 AM", AddTimeUtil.addMinutes("12:00 AM", 0));
	}
}
