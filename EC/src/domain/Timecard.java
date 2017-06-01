package domain;

import utils.BadParameterException;
import utils.NullParameterException;

/**
 * A class representing an Employee's Timecard.<br>
 * Usage example:
 * 
 * <pre>
 * Timecard myTimecard = new Timecard(new int[] { 5, 6, 7, 8, 9 });
 * Timecard myTimecard = new Timecard(anotherTimecard);
 * </pre>
 * 
 * @author Christopher Hield
 * @see domain.Department
 * @see domain.Employee
 * @see domain.SalesEmployee
 * @since Version 1.8
 *
 */
public class Timecard {

	/**
	 * The number of working days in a Week ({@value})
	 */
	public static final int NUM_DAYS = 5;

	/**
	 * Array representing the days of the Week.
	 */
	private final int[] daysOfTheWeek = new int[NUM_DAYS];

	/**
	 * Constructor for the Timecard class that takes an array of numbers that represents the days of the week.
	 * 
	 * @param daysIn
	 *            Array of numbers representing the days of the week
	 * @throws NullParameterException
	 *             If provided array is null
	 * @throws BadParameterException
	 *             If the size of the provided array is not equal to NUM_DAYS
	 * @see #Timecard(Timecard)
	 */
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

	/**
	 * Constructor for the Timecard class that takes another instance of Timecard as parameter.
	 * 
	 * @param t
	 *            Another instance of Timecard
	 * @throws BadParameterException
	 *             If number of hours extracted from passed parameter are less than zero or more than 24
	 * @see #Timecard(int[])
	 */
	public Timecard(Timecard t) throws BadParameterException {
		for (int i = 0; i < NUM_DAYS; i++) {
			setHoursByDay(i, t.getHoursByDay(i));
		}
	}

	/**
	 * Returns the Weekly Hours in the Timecard. The result is all the hours in the Timecard added up
	 * 
	 * @return Returns the Weekly Hours in the Timecard
	 * @see #getHoursByDay(int)
	 */
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

	/**
	 * Returns the number of hours for a given day passed as parameter.
	 * 
	 * @param day
	 *            Day of the Week starting from 1
	 * @return Returns the number of hours for a given day
	 * @throws BadParameterException
	 *             If the day passed as a parameter is less than zero, or more or equal than NUM_DAYS
	 * @see #setHoursByDay(int, int)
	 */
	public int getHoursByDay(int day) throws BadParameterException {
		if (day < 0 || day >= NUM_DAYS) {
			throw new BadParameterException("Bad day value passed to getHoursByDay: " + day);
		}

		return daysOfTheWeek[day];
	}

	/**
	 * Sets the number of worked hours for a given day
	 * 
	 * @param day
	 *            Day of the week starting from 1
	 * @param hours
	 *            Hour for that specific day
	 * @throws BadParameterException
	 *             If day passed as parameter is less than zero or more or equal to NUM_DAYS. If hours passed as parameter are less than zero or more
	 *             than 24
	 * @see #getHoursByDay(int)
	 */
	private void setHoursByDay(int day, int hours) throws BadParameterException {
		if (day < 0 || day >= NUM_DAYS) {
			throw new BadParameterException("Bad day value passed to setHoursByDay: " + day);
		}
		if (hours < 0 || hours > 24) {
			throw new BadParameterException("Bad hours value passed to setHoursByDay: " + hours);
		}
		daysOfTheWeek[day] = hours;
	}

	/**
	 * Returns a formatted report with all the information about the Timecard.
	 * 
	 * @return Returns a formatted report with all the information about the Timecard
	 */
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
