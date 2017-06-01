/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import utils.BadParameterException;
import utils.NullParameterException;

/**
 * A class representing an Employee.<br>
 * Usage example:
 * 
 * <pre>
 * Employee myEmployee = new Employee("Christopher", "Hield", 5000, 50.0, new int[]{5, 6, 7, 8, 9});
 * Employee myEmployee = new Employee(Employee otherEmployee);
 * </pre>
 * 
 * @author Christopher Hield
 * @see domain.Department
 * @see domain.SalesEmployee
 * @see domain.Timecard
 * @since Version 1.8
 *
 */
public class Employee implements Comparable<Employee> {

	/**
	 * The first name of the Employee.
	 * 
	 * @see #setFirstName(String)
	 * @see #getFirstName()
	 */
	private String firstName;

	/**
	 * The last name of the Employee.
	 * 
	 * @see #setLastName(String)
	 * @see #getLastName()
	 */
	private String lastName;

	/**
	 * The ID of the Employee.
	 * 
	 * @see #setEmployeeId(int)
	 * @see #getEmployeeId()
	 */
	private int employeeId;

	/**
	 * The Hourly Rate of the Employee.
	 * 
	 * @see #setHourlyRate(double)
	 * @see #getHourlyRate()
	 */
	private double hourlyRate;

	/**
	 * The Timecard of the Employee. The Timecard holds all the information regarding the worked hours during the week.
	 * 
	 * @see #setTimecard(Timecard)
	 * @see #getTimecard()
	 */
	private Timecard timecard;

	/**
	 * Constructor for the Employee class - need non-null and non-empty First and Last name, an Employee ID between 1000 and 9999, a higher than zero
	 * Hourly Rate, and an int Array holding the worked days.
	 * 
	 * @param fName
	 *            The Employee's first name
	 * @param lName
	 *            The Employee's last name
	 * @param eId
	 *            The Employee's ID
	 * @param hRate
	 *            The Employee's Hourly Rate
	 * @param daysIn
	 *            The Employee's int Array with the worked days
	 * @throws NullParameterException
	 *             If either First name or Last name parameters are Null
	 * @throws BadParameterException
	 *             If either First name or Last name have are longer than 20 characters. If ID is not between 1000 and 9999. If Hourly Rate is less or
	 *             equal to zero
	 * @see #Employee(Employee)
	 */
	public Employee(String fName, String lName, int eId, double hRate, int[] daysIn)
			throws NullParameterException, BadParameterException {
		setFirstName(fName);
		setLastName(lName);
		setEmployeeId(eId);
		setHourlyRate(hRate);
		setTimecard(new Timecard(daysIn));
	}

	/**
	 * Constructor for the Employee class that takes another instance of Employee as parameter.
	 * 
	 * @param e
	 *            Another instance of the Employee class
	 * @throws NullParameterException
	 *             If either First name or Last name parameters extracted from passed Employee are Null
	 * @throws BadParameterException
	 *             If either First name or Last name parameters extracted from passed Employee have are longer than 20 characters. If ID parameter
	 *             extracted from passed Employee is not between 1000 and 9999. If Hourly Rate parameter extracted from passed Employee is less or
	 *             equal to zero
	 * @see #Employee(String, String, int, double, int[])
	 */
	public Employee(Employee e) throws NullParameterException, BadParameterException {
		setFirstName(e.getFirstName());
		setLastName(e.getLastName());
		setEmployeeId(e.getEmployeeId());
		setHourlyRate(e.getHourlyRate());
		setTimecard(new Timecard(e.getTimecard()));
	}

	/**
	 * Compares the Employee with another Employee passed as parameter based on their Employee ID.
	 * 
	 * @return Returns -1 if ID number of passed Employee is larger. Returns 0 if they have the same ID. Returns 1 if ID number of passed Employee is
	 *         smaller.
	 */
	@Override
	public int compareTo(Employee eIn) {
		if (getEmployeeId() < eIn.getEmployeeId()) {
			return -1;
		} else if (getEmployeeId() == eIn.getEmployeeId()) {
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * Returns the Weekly Pay of the Employee.
	 * 
	 * @see #setHourlyRate(double)
	 * @return Returns the Weekly Pay of the Employee
	 */
	public double getWeeklyPay() {
		return getTimecard().getWeeklyHours() * getHourlyRate();
	}

	/**
	 * Returns the First name of the Employee.
	 * 
	 * @see #setFirstName(String)
	 * @return Returns the First name of the Employee
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the provided name as the Fist name of the Employee.
	 * 
	 * @param fName
	 *            The Employee's First name
	 * @throws NullParameterException
	 *             If the provided name is null
	 * @throws BadParameterException
	 *             If the provided name length is either zero or less, or more than 20.
	 * @see #getFirstName()
	 */
	public final void setFirstName(String fName) throws NullParameterException, BadParameterException {
		if (fName == null) {
			throw new NullParameterException("Null value passed in for firstName");
		}

		if (fName.length() <= 0 || fName.length() > 20) {
			throw new BadParameterException("Bad value passed in for firstName: " + fName);
		}
		firstName = fName;
	}

	/**
	 * Returns the Last name of the Employee.
	 * 
	 * @see #setLastName(String)
	 * @return Returns the Last name of the Employee
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the provided name as the Last name of the Employee.
	 * 
	 * @param lName
	 *            The Employee's Last name
	 * @throws NullParameterException
	 *             If the provided name is null
	 * @throws BadParameterException
	 *             If the provided name length is either zero or less, or more than 20.
	 * @see #getLastName()
	 */
	public final void setLastName(String lName) throws NullParameterException, BadParameterException {
		if (lName == null) {
			throw new NullParameterException("Null value passed in for lastName");
		}

		if (lName.length() <= 0 || lName.length() > 20) {
			throw new BadParameterException("Bad value passed in for lastName: " + lName);
		}
		lastName = lName;
	}

	/**
	 * Returns the ID of the Employee.
	 * 
	 * @see #setEmployeeId(int)
	 * @return Returns the ID of the Employee
	 */
	public int getEmployeeId() {
		return employeeId;
	}

	/**
	 * Sets the provided number as the ID of the Employee
	 * 
	 * @param eId
	 *            The Employee's ID number
	 * @throws BadParameterException
	 *             If the provided number is either less than 1000 or more than 9999
	 * @see #getEmployeeId()
	 */
	public final void setEmployeeId(int eId) throws BadParameterException {
		if (eId < 1000 || eId > 9999) {
			throw new BadParameterException("Bad value passed in for employeeId: " + eId);
		}
		employeeId = eId;
	}

	/**
	 * Returns the Hourly Rate of the Employee.
	 * 
	 * @see #setHourlyRate(double)
	 * @return Returns the Hourly Rate of the Employee
	 */
	public double getHourlyRate() {
		return hourlyRate;
	}

	/**
	 * Sets the provided number as the Hourly Rate of the Employee.
	 * 
	 * @param hRate
	 *            The Employee's Hourly Rate.
	 * @throws BadParameterException
	 *             If the provided number is less or equal to 0.0
	 * @see #getHourlyRate()
	 */
	public final void setHourlyRate(double hRate) throws BadParameterException {
		if (hRate <= 0.0) {
			throw new BadParameterException("Bad value passed in for hourlyRate: " + hRate);
		}
		hourlyRate = hRate;
	}

	/**
	 * Returns the Timecard instance of the Employee.
	 * 
	 * @see #setTimecard(Timecard)
	 * @return Returns the Timecard instance of the Employee
	 */
	private Timecard getTimecard() {
		return timecard;
	}

	/**
	 * Sets the provided Timecard as the Timecard of the Employee.
	 * 
	 * @param tCard
	 *            The Employee's Timecard
	 * @throws NullParameterException
	 *             If the provided Timecard is null
	 * @see #getTimecard()
	 */
	private void setTimecard(Timecard tCard) throws NullParameterException {
		if (tCard == null) {
			throw new NullParameterException("Null Timecard passed to setTimecard");
		}
		timecard = tCard;
	}

	/**
	 * Returns a formatted report with all the information about the Employee.
	 * 
	 * @return Returns a formatted report with all the information about the Employee
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("%-20s %s %s%n", "Name:", getFirstName(), getLastName()));
		sb.append(String.format("%-20s %d%n", "Id:", getEmployeeId()));
		sb.append(String.format("%-20s $%.2f%n", "Hourly Rate:", getHourlyRate()));
		sb.append(String.format("%s", getTimecard()));
		sb.append(String.format("%-20s $%.2f%n", "Weekly Pay:", getWeeklyPay()));

		return sb.toString();
	}
}
