package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.SalesEmployee;
import utils.BadParameterException;
import utils.NullParameterException;

public class SalesEmployeeTest {

	private SalesEmployee testSalesEmployee;
	private String fName;
	private String lName;
	private int id;
	private double salesNeeded;
	private double weeklyTotal;
	private double hRate;
	private int[] days;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {}

	@Before
	public void setUp() throws Exception {
		try {
			fName = "Miguel";
			lName = "Ledezma";
			id = 1234;
			hRate = 100;
			days = new int[] { 1, 2, 3, 4, 5 };
			salesNeeded = 1000;
			weeklyTotal = 2000;
			testSalesEmployee = new SalesEmployee(fName, lName, id, hRate, days, salesNeeded, weeklyTotal);
		} catch (NullParameterException | BadParameterException e) {
			fail("Creation of test SalesEmployee object in @Before failed: " + e.getMessage());
		}
	}

	@After
	public void tearDown() throws Exception {}

	@Test
	public void testGetWeeklyPay() throws NullParameterException, BadParameterException {
		assertTrue(testSalesEmployee.getWeeklyPay() == weeklyTotal * SalesEmployee.BONUS_MULTIPLIER);

		salesNeeded = 1001;
		weeklyTotal = 1000;
		testSalesEmployee = new SalesEmployee(fName, lName, id, hRate, days, salesNeeded, weeklyTotal);
		assertTrue(testSalesEmployee.getWeeklyPay() == 15 * hRate);
	}

	@Test
	public void testToString() {
		assertTrue(testSalesEmployee.toString().contains("Sales Amount Needed:"));
	}

	@Test
	public void testSalesEmployeeStringStringIntDoubleIntArrayDoubleDouble() throws NullParameterException {
		try {
			new SalesEmployee(fName, lName, id, hRate, days, salesNeeded, weeklyTotal);
			assertEquals(id, testSalesEmployee.getEmployeeId());
		} catch (NullParameterException e) {
			fail("NullParameterException (" + e.getMessage()
					+ ") thrown from SalesEmployee c'tor with valid parameters");
		} catch (BadParameterException e) {
			fail("BadParameterException (" + e.getMessage()
					+ ") thrown from SalesEmployee c'tor with valid parameters");
		}

		salesNeeded = 0;
		try {
			testSalesEmployee = new SalesEmployee(fName, lName, id, hRate, days, salesNeeded, weeklyTotal);
			fail("BadParamenterException NOT thrown from SalesEmployee c'tor with salesNeeded = 0");
		} catch (BadParameterException e) {
			assertTrue(e.getMessage().startsWith("Invalid sales amount needed"));
			assertFalse(testSalesEmployee.getSalesAmountNeeded() == salesNeeded);
		}

		salesNeeded = 1000;
		weeklyTotal = 0;
		try {
			testSalesEmployee = new SalesEmployee(fName, lName, id, hRate, days, salesNeeded, weeklyTotal);
			fail("BadParamenterException NOT thrown from SalesEmployee c'tor with Weekly Sales Total = 0");
		} catch (BadParameterException e) {
			assertTrue(e.getMessage().startsWith("Invalid weekly sales total"));
			assertFalse(testSalesEmployee.getWeeklySalesTotal() == weeklyTotal);
		}
	}

	@Test
	public void testSalesEmployeeSalesEmployee() {
		try {
			SalesEmployee testSalesEmployee2 = new SalesEmployee(testSalesEmployee);
			assertNotSame(testSalesEmployee, testSalesEmployee2);
		} catch (NullParameterException | BadParameterException e) {
			fail("Creation of test SalesEmployee object in SalesEmployee(SalesEmployee) failed: " + e.getMessage());
		}
	}

	@Test
	public void testGetSalesAmountNeeded() {
		assertTrue(testSalesEmployee.getSalesAmountNeeded() == salesNeeded);
	}

	@Test
	public void testGetWeeklySalesTotal() {
		assertTrue(testSalesEmployee.getWeeklySalesTotal() == weeklyTotal);
	}

}
