
package domain;

import utils.BadParameterException;
import utils.NullParameterException;

public class SalesEmployee extends Employee {

	private double salesAmountNeeded;
	private double weeklySalesTotal;
	public static final double BONUS_MULTIPLIER = 0.25;

	public SalesEmployee(String fName, String lName, int eId, double hRate, int[] daysIn, double salesNeeded,
			double weeklyTotal) throws NullParameterException, BadParameterException {
		super(fName, lName, eId, hRate, daysIn);
		setSalesAmountNeeded(salesNeeded);
		setWeeklySalesTotal(weeklyTotal);
	}

	public SalesEmployee(SalesEmployee se) throws NullParameterException, BadParameterException {
		super(se);
		setSalesAmountNeeded(se.getSalesAmountNeeded());
		setWeeklySalesTotal(se.getWeeklySalesTotal());
	}

	public double getSalesAmountNeeded() {
		return salesAmountNeeded;
	}

	public double getWeeklySalesTotal() {
		return weeklySalesTotal;
	}

	private void setSalesAmountNeeded(double d) throws BadParameterException {
		if (d <= 0.0) {
			throw new BadParameterException("Invalid sales amount needed: " + d);
		}
		salesAmountNeeded = d;
	}

	private void setWeeklySalesTotal(double totalIn) throws BadParameterException {
		if (totalIn <= 0.0) {
			throw new BadParameterException("Invalid weekly sales total: " + totalIn);
		}
		weeklySalesTotal = totalIn;
	}

	@Override
	public double getWeeklyPay() {

		if (getWeeklySalesTotal() >= getSalesAmountNeeded()) {
			return getWeeklySalesTotal() * BONUS_MULTIPLIER;
		}

		return super.getWeeklyPay();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(super.toString());
		sb.append(String.format("%-20s $%.2f%n", "Sales Amount Needed:", getSalesAmountNeeded()));
		sb.append(String.format("%-20s $%.2f%n", "Weekly Sales Total:", getWeeklySalesTotal()));

		return sb.toString();
	}
}
