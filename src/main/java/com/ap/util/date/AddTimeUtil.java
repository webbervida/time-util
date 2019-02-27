package com.ap.util.date;

import java.util.regex.Pattern;

/**
 * The {@code AddTimeUtil} class is a utility to add from subtract minutes on
 * formated times.
 *
 * @author John Doe
 */
public class AddTimeUtil {

	private static final Pattern pattern = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9] [AP][M]");

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
		}
		return null;
	}

	public static boolean isValidFormat(final String time) {
		return pattern.matcher(time).matches();
	}
}
