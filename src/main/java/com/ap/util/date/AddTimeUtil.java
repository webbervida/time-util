package com.ap.util.date;

import java.util.regex.Pattern;

/**
 * The {@code AddTimeUtil} class is a utility to add from subtract minutes on
 * formated times.
 *
 * @author John Doe
 */
public class AddTimeUtil {

	private enum Meridiem {
		AM, PM
	}

	private static final String TIME_TEMPLATE = "%s:%02d %s";

	private static final Pattern pattern = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9] [AP][M]");

	private static final byte HOURS_PER_MERIDIEM = 12;

	private static final byte HOURS_IN_DAY = HOURS_PER_MERIDIEM * 2;

	private static final byte MINUTES_IN_HOUR = 60;

	private static final short MINUTES_IN_MERIDIEM = HOURS_PER_MERIDIEM * MINUTES_IN_HOUR;

	private static final short MINUTES_IN_DAY = HOURS_IN_DAY * MINUTES_IN_HOUR;

	private AddTimeUtil() {
	}

	/**
	 * Returns the formatted {@code String} time with offset added or subtracted to
	 * time passed in.
	 *
	 * @param time   the time in [H]H:MM {AM|PM} format.
	 * @param offset the amount of time in minutes to add or subtract.
	 * @return a time {@code String} that has been adjusted for the passed in number
	 *         of offsetMinutes.
	 * @exception IllegalArgumentException if {@code time} is in incorrect format.
	 */
	public static String addMinutes(final String time, final int offset) {

		if (time == null || !isValidFormat(time)) {
			throw new IllegalArgumentException("Time not in correct format");
		} else if (offset == 0) {
			return time;
		}

		int minuteOfTheDay = getMinuteOfTheDay(time);

		int minuteOfTheDayWithOffset = getAdjustedMinuteOfTheDay(minuteOfTheDay, offset);

		return getFormattedTimeForMinuteOfTheDay(minuteOfTheDayWithOffset);
	}

	private static int getMinuteOfTheDay(final String time) {
		// Confidence in 'bucketing' items correctly from earlier regex'ing of String
		String[] hourMinuteMeridiem = time.split("[: ]");

		int hour = Integer.parseInt(hourMinuteMeridiem[0]);
		int minute = Integer.parseInt(hourMinuteMeridiem[1]);
		String meridiem = hourMinuteMeridiem[2];

		if (meridiem.equals(Meridiem.AM.name())) {
			if (hour == HOURS_PER_MERIDIEM) {
				hour = 0;
			}
			return (MINUTES_IN_HOUR * hour) + minute;
		} else {
			return (MINUTES_IN_HOUR * hour) + MINUTES_IN_MERIDIEM + minute;
		}
	}

	private static int getAdjustedMinuteOfTheDay(final int minuteOfTheDay, final int offset) {
		int endingMinute = minuteOfTheDay + offset;
		int leftOver = endingMinute % MINUTES_IN_DAY;
		if (endingMinute < 0) {
			return MINUTES_IN_DAY - Math.abs(leftOver);
		} else {
			return leftOver;
		}
	}

	private static String getFormattedTimeForMinuteOfTheDay(final int minuteOfTheDay) {
		Meridiem meridiem = (minuteOfTheDay <= MINUTES_IN_MERIDIEM) ? Meridiem.AM : Meridiem.PM;

		int prettyHour = (minuteOfTheDay / MINUTES_IN_HOUR == 0) ? HOURS_PER_MERIDIEM
				: minuteOfTheDay / MINUTES_IN_HOUR;
		prettyHour = (prettyHour > HOURS_PER_MERIDIEM) ? prettyHour - HOURS_PER_MERIDIEM : prettyHour;
		return String.format(TIME_TEMPLATE, prettyHour, minuteOfTheDay % MINUTES_IN_HOUR, meridiem);
	}

	/**
	 * Utility method to allow pre-checking of time Strings to allow
	 * 
	 * @param time the time that should be in [H]H:MM {AM|PM} format.
	 * @return whether or not passed in String time is valid
	 */
	public static boolean isValidFormat(final String time) {
		return pattern.matcher(time).matches();
	}
}
