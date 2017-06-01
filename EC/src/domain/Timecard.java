package domain;

import utils.BadParameterException;
import utils.NullParameterException;

public class Timecard {

	public static final int NUM_DAYS = 5;
	private final int[] daysOfTheWeek = new int[NUM_DAYS];

	public Timecard(int[] daysIn) throws NullParameterException, BadParameterException {
		if (daysIn == null) {
			throw new NullParameterException("Null int array passed to Timecard c'tor");
		}
		if (daysIn.length != NUM_DAYS) {
			throw new BadParameterException("Invalid int array passed to Timecard c'tor, length: " + daysIn.length);
		}
		for (int i = 0; i < NUM_DAYS; i++) {
			setHoursByDay(i, daysIn[i]);
		}
	}

	public Timecard(Timecard t) throws BadParameterException {
		for (int i = 0; i < NUM_DAYS; i++) {
			setHoursByDay(i, t.getHoursByDay(i));
		}
	}

	public int getWeeklyHours() {
		int count = 0;
		for (int i = 0; i < NUM_DAYS; i++) {
			try {
				count += getHoursByDay(i);
			} catch (BadParameterException e) {
				System.err.println("Invalid day " + i + " skipped in summing weekly hours");
			}
		}
		return count;
	}

	public int getHoursByDay(int day) throws BadParameterException {
		if (day < 0 || day >= NUM_DAYS) {
			throw new BadParameterException("Bad day value passed to getHoursByDay: " + day);
		}

		return daysOfTheWeek[day];
	}

	private void setHoursByDay(int day, int hours) throws BadParameterException {
		if (day < 0 || day >= NUM_DAYS) {
			throw new BadParameterException("Bad day value passed to setHoursByDay: " + day);
		}
		if (hours < 0 || hours > 24) {
			throw new BadParameterException("Bad hours value passed to setHoursByDay: " + hours);
		}
		daysOfTheWeek[day] = hours;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%-20s %d%n", "Weekly Hours:", getWeeklyHours()));
		for (int i = 0; i < NUM_DAYS; i++) {
			try {
				sb.append(String.format("%7s %d: %11d%n", "Day", (i + 1), getHoursByDay(i)));
			} catch (BadParameterException e) {
				sb.append(String.format("%7s %d: %s %d%n", "Day", (i + 1), "Error Accessing Hours for Day", i));
			}
		}
		return sb.toString();
	}
}
