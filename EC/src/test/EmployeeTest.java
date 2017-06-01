package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.Employee;
import utils.BadParameterException;
import utils.NullParameterException;

public class EmployeeTest {

	private Employee testEmployee;
	private String fName;
	private String lName;
	private int id;
	private double hRate;
	private int[] days;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {}

	@Before
	public void setUp() {
		try {
			fName = "Miguel";
			lName = "Ledezma";
			id = 1234;
			hRate = 100;
			days = new int[] { 1, 2, 3, 4, 5 };
			testEmployee = new Employee(fName, lName, id, hRate, days);
		} catch (NullParameterException | BadParameterException e) {
			fail("Creation of test Employee object in @Before failed: " + e.getMessage());
		}
	}

	@After
	public void tearDown() throws Exception {}

	@Test
	public void testEmployeeStringStringIntDoubleIntArray() {
		try {
			new Employee(fName, lName, id, hRate, days);
			assertEquals(id, testEmployee.getEmployeeId());
		} catch (NullParameterException e) {
			fail("NullParameterException (" + e.getMessage() + ") thrown from Employee c'tor with valid parameters");
		} catch (BadParameterException e) {
			fail("BadParameterException (" + e.getMessage() + ") thrown from Employee c'tor with valid parameters");
		}
	}

	@Test
	public void testEmployeeEmployee() {
		try {
			Employee testEmployee2 = new Employee(testEmployee);
			assertNotSame(testEmployee, testEmployee2);
		} catch (NullParameterException | BadParameterException e) {
			fail("Creation of test Employee object in Employee(SalesEmployee) failed: " + e.getMessage());
		}
	}

	@Test
	public void testCompareTo() throws NullParameterException, BadParameterException {
		Employee testEmployee2 = new Employee("John", "DePaul", 1235, 100, new int[] { 1, 2, 3, 4, 5 });
		assertTrue(testEmployee.compareTo(testEmployee2) == -1);

		testEmployee2 = new Employee("John", "DePaul", 1234, 100, new int[] { 1, 2, 3, 4, 5 });
		assertTrue(testEmployee.compareTo(testEmployee2) == 0);

		testEmployee2 = new Employee("John", "DePaul", 1233, 100, new int[] { 1, 2, 3, 4, 5 });
		assertTrue(testEmployee.compareTo(testEmployee2) == 1);
	}

	@Test
	public void testGetWeeklyPay() {
		assertTrue(testEmployee.getWeeklyPay() == 15 * 100);
	}

	@Test
	public void testGetFirstName() {
		assertEquals("Miguel", testEmployee.getFirstName());
	}

	@Test
	public void testSetFirstName() {
		String firstName = "John";
		try {
			testEmployee.setFirstName(firstName);
			assertEquals(firstName, testEmployee.getFirstName());
		} catch (NullParameterException e) {
			fail("NullParameterException (" + e.getMessage()
					+ ") thrown from setFirstName(String) with valid parameters");
		} catch (BadParameterException e) {
			fail("BadParameterException (" + e.getMessage()
					+ ") thrown from setFirstName(String) with valid parameters");
		}

		firstName = null;
		try {
			testEmployee.setFirstName(firstName);
			fail("NullParameterException NOT thrown from setFirstName(String) with null parameters");
		} catch (NullParameterException e) {
			assertEquals("Null value passed in for firstName", e.getMessage());
		} catch (BadParameterException e) {
			fail("Wrong Exception (BadParameterException) thrown from setFirstName(String) with a Null Parameter");
		}

		firstName = "Miguel Angel Ledezma Sarmiento";
		try {
			testEmployee.setFirstName(firstName);
			fail("BadParameterException NOT thrown from setFirstName(String) with a name larger than 20");
		} catch (NullParameterException e) {
			fail("Wrong Exception (NullParameterException) thrown from setFirstName(String) with a name larger than 20");
		} catch (BadParameterException e) {
			assertTrue(e.getMessage().startsWith("Bad value passed in for firstName"));
		}
	}

	@Test
	public void testGetLastName() {
		assertEquals("Ledezma", testEmployee.getLastName());
	}

	@Test
	public void testSetLastName() {
		String lastName = "Johnson";
		try {
			testEmployee.setLastName(lastName);
			assertEquals(lastName, testEmployee.getLastName());
		} catch (NullParameterException e) {
			fail("NullParameterException (" + e.getMessage()
					+ ") thrown from setLastName(String) with valid parameters");
		} catch (BadParameterException e) {
			fail("BadParameterException (" + e.getMessage()
					+ ") thrown from setLastName(String) with valid parameters");
		}

		lastName = null;
		try {
			testEmployee.setLastName(lastName);
			fail("NullParameterException NOT thrown from setLastName(String) with null parameters");
		} catch (NullParameterException e) {
			assertEquals("Null value passed in for lastName", e.getMessage());
		} catch (BadParameterException e) {
			fail("Wrong Exception (BadParameterException) thrown from setLastName(String) with a Null Parameter");
		}

		lastName = "Miguel Angel Ledezma Sarmiento";
		try {
			testEmployee.setLastName(lastName);
			fail("BadParameterException NOT thrown from setLastName(String) with a Last name larger than 20");
		} catch (NullParameterException e) {
			fail("Wrong Exception (NullParameterException) thrown from setLastName(String) with a Last name larger than 20");
		} catch (BadParameterException e) {
			assertTrue(e.getMessage().startsWith("Bad value passed in for lastName"));
		}
	}

	@Test
	public void testGetEmployeeId() {
		assertEquals(1234, testEmployee.getEmployeeId());
	}

	@Test
	public void testSetEmployeeId() {
		int id = 1000;
		try {
			testEmployee.setEmployeeId(id);
			assertEquals(id, testEmployee.getEmployeeId());
		} catch (BadParameterException e) {
			fail("BadParameterException (" + e.getMessage() + ") thrown from setEmployeeId(int) with valid parameters");
		}

		id = 9999;
		try {
			testEmployee.setEmployeeId(id);
			assertEquals(id, testEmployee.getEmployeeId());
		} catch (BadParameterException e) {
			fail("BadParameterException (" + e.getMessage() + ") thrown from setEmployeeId(int) with valid parameters");
		}

		id = 999;
		try {
			testEmployee.setEmployeeId(id);
			fail("BadParameterException NOT thrown from setEmployeeId(int) with lower than minimum parameters");
		} catch (BadParameterException e) {
			assertTrue(e.getMessage().startsWith("Bad value passed in for employeeId"));
		}

		id = 10000;
		try {
			testEmployee.setEmployeeId(id);
			fail("BadParameterException NOT thrown from setEmployeeId(int) with higher than minimum parameters");
		} catch (BadParameterException e) {
			assertTrue(e.getMessage().startsWith("Bad value passed in for employeeId"));
		}
	}

	@Test
	public void testGetHourlyRate() {
		assertTrue(testEmployee.getHourlyRate() == 100);
	}

	@Test
	public void testSetHourlyRate() {
		double hourlyRate = 0.1;
		try {
			testEmployee.setHourlyRate(hourlyRate);
			;
			assertTrue(testEmployee.getHourlyRate() == hourlyRate);
		} catch (BadParameterException e) {
			fail("BadParameterException (" + e.getMessage()
					+ ") thrown from setHourlyRate(double) with valid parameters");
		}

		hourlyRate = 0;
		try {
			testEmployee.setHourlyRate(hourlyRate);
			;
			fail("BadParameterException NOT thrown from setHourlyRate(double) with lower than minimum parameters");
		} catch (BadParameterException e) {
			assertTrue(e.getMessage().startsWith("Bad value passed in for hourlyRate"));
		}
	}

	@Test
	public void testToString() {
		assertTrue(testEmployee.toString().contains("Weekly Pay:"));
	}

}
