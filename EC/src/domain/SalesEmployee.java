
package domain;

import utils.BadParameterException;
import utils.NullParameterException;

/**
 * A class representing a Sales Employee. This class is a child of the class Employee<br>
 * Usage example:
 * 
 * <pre>
 * SalesEmployee myEmployee = new SalesEmployee("Christopher", "Hield", 5000, 50.0, new int[]{5, 6, 7, 8, 9}, 1000, 2000);
 * SalesEmployee myEmployee = new SalesEmployee(SalesEmployee otherSalesEmployee);
 * </pre>
 * 
 * @author Christopher Hield
 * @see domain.Department
 * @see domain.Employee
 * @see domain.Timecard
 * @since Version 1.8
 *
 */
public class SalesEmployee extends Employee {

	/**
	 * Represents the Sales amount needed by the Sales Employee to earn by commission. If this amount is not met he earns the basic salary.
	 * 
	 * @see #getSalesAmountNeeded()
	 * @see #setSalesAmountNeeded(double)
	 */
	private double salesAmountNeeded;

	/**
	 * Represents the Sales Employee's weekly total sales. This total is useful to determine if the Sales Employee earn by commission.
	 * 
	 * @see #getWeeklySalesTotal()
	 * @see #setWeeklySalesTotal(double)
	 */
	private double weeklySalesTotal;

	/**
	 * The Bonus Multiplier used to determine the commission amount for the Weekly Pay ({@value})
	 * 
	 * @see #getWeeklyPay()
	 */
	public static final double BONUS_MULTIPLIER = 0.25;

	/**
	 * Constructor for the Sales Employee class - need non-null and non-empty First and Last name, a Sales Employee ID between 1000 and 9999, a higher
	 * than zero Hourly Rate, an int Array holding the worked days, sales needed to earn by commission, and a weekly total.
	 * 
	 * @param fName
	 *            The Sales Employee's first name
	 * @param lName
	 *            The Sales Employee's last name
	 * @param eId
	 *            The Sales Employee's ID
	 * @param hRate
	 *            The Sales Employee's Hourly Rate
	 * @param daysIn
	 *            The Sales Employee's int Array with the worked days
	 * @param salesNeeded
	 *            The Sales Employee's needed sales to earn by commission
	 * @param weeklyTotal
	 *            The Sales Employee's weekly total sales
	 * @throws NullParameterException
	 *             If either First name or Last name parameters are Null
	 * @throws BadParameterException
	 *             If either First name or Last name have are longer than 20 characters. If ID is not between 1000 and 9999. If Hourly Rate is less or
	 *             equal to zero. If Sales Needed or Weekly Total is less or equal to zero
	 * @see #SalesEmployee(SalesEmployee)
	 */
	public SalesEmployee(String fName, String lName, int eId, double hRate, int[] daysIn, double salesNeeded,
			double weeklyTotal) throws NullParameterException, BadParameterException {
		super(fName, lName, eId, hRate, daysIn);
		setSalesAmountNeeded(salesNeeded);
		setWeeklySalesTotal(weeklyTotal);
	}

	/**
	 * Constructor for the Sales Employee class that takes another instance of Sales Employee as parameter.
	 * 
	 * @param se
	 *            Another instance of the Sales Employee class
	 * @throws NullParameterException
	 *             If either First name or Last name parameters extracted from passed Sales Employee are Null
	 * @throws BadParameterException
	 *             If either First name or Last name parameters extracted from passed Sales Employee have are longer than 20 characters. If ID
	 *             parameter extracted from passed Sales Employee is not between 1000 and 9999. If Hourly Rate parameter extracted from passed Sales
	 *             Employee is less or equal to zero. If Sales Needed or Weekly Total extracted from passes Sales Employee is less or equal to zero
	 * @see #SalesEmployee(String, String, int, double, int[], double, double)
	 */
	public SalesEmployee(SalesEmployee se) throws NullParameterException, BadParameterException {
		super(se);
		setSalesAmountNeeded(se.getSalesAmountNeeded());
		setWeeklySalesTotal(se.getWeeklySalesTotal());
	}

	/**
	 * Returns the minimum Sales amount needed by the Sales Employee to earn by commission.
	 * 
	 * @return Returns the minimum Sales amount needed by the Sales Employee to earn by commission.
	 * @see #setSalesAmountNeeded(double)
	 */
	public double getSalesAmountNeeded() {
		return salesAmountNeeded;
	}

	/**
	 * Return the Total Weekly Sales of the Employee.
	 * 
	 * @return Return the Total Weekly Sales of the Employee
	 * @see #setWeeklySalesTotal(double)
	 */
	public double getWeeklySalesTotal() {
		return weeklySalesTotal;
	}

	/**
	 * Sets the provided number as the Sales amount needed by the Sales Employee to earn by commission.
	 * 
	 * @param d
	 *            Sales Amount needed by the Sales Employee to earn by commission
	 * @throws BadParameterException
	 *             If provided number is less or equal to zero
	 * @see #getSalesAmountNeeded()
	 */
	private void setSalesAmountNeeded(double d) throws BadParameterException {
		if (d <= 0.0) {
			throw new BadParameterException("Invalid sales amount needed: " + d);
		}
		salesAmountNeeded = d;
	}

	/**
	 * Sets the provided number as the Total Weekly Sales of the Sales Employee.
	 * 
	 * @param totalIn
	 *            The Sales Employee's Total Weekly Sales
	 * @throws BadParameterException
	 *             If the provided number is less or equal to zero
	 * @see #getWeeklySalesTotal()
	 */
	private void setWeeklySalesTotal(double totalIn) throws BadParameterException {
		if (totalIn <= 0.0) {
			throw new BadParameterException("Invalid weekly sales total: " + totalIn);
		}
		weeklySalesTotal = totalIn;
	}

	/**
	 * Returns the Weekly Pay of the Sales Employee. This method overrides the parent method. If the Sales Employee meets the Sales Amount Needed the
	 * Weekly Pay is equal to the Weekly Sales Total * BONUS_MULTIPLIER, else it's the Weekly Pay of a regular Employee.
	 * 
	 * @return Returns the Sales Employee Weekly Pay
	 * @see domain.Employee
	 */
	@Override
	public double getWeeklyPay() {

		if (getWeeklySalesTotal() >= getSalesAmountNeeded()) {
			return getWeeklySalesTotal() * BONUS_MULTIPLIER;
		}

		return super.getWeeklyPay();
	}

	/**
	 * Returns a formatted report with all the information about the Sales Employee.
	 * 
	 * @return Returns a formatted report with all the information about the Sales Employee
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(super.toString());
		sb.append(String.format("%-20s $%.2f%n", "Sales Amount Needed:", getSalesAmountNeeded()));
		sb.append(String.format("%-20s $%.2f%n", "Weekly Sales Total:", getWeeklySalesTotal()));

		return sb.toString();
	}
}
