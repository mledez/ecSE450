package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.Timecard;
import utils.BadParameterException;
import utils.NullParameterException;

public class TimecardTest {

	private int[] testDaysIn;
	private Timecard testTimecard;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {}

	@Before
	public void setUp() {
		try {
			testDaysIn = new int[] { 5, 6, 7, 8, 9 };
			testTimecard = new Timecard(testDaysIn);
		} catch (NullParameterException | BadParameterException e) {
			fail("Creation of test timecard object in @Before failed: " + e.getMessage());
		}
	}

	@After
	public void tearDown() throws Exception {}

	@Test
	public void testTimecardIntArray() {
		try {
			testTimecard = new Timecard(testDaysIn);
			assertEquals(testDaysIn[0], testTimecard.getHoursByDay(0));
		} catch (NullParameterException e) {
			fail("NullParameterException (" + e.getMessage() + ") thrown from Timecard(int[]) with valid parameters");
		} catch (BadParameterException e) {
			fail("BadParameterException (" + e.getMessage() + ") thrown from Timecard(int[]) with valid parameters");
		}

		testDaysIn = null;
		try {
			testTimecard = new Timecard(testDaysIn);
			fail("NullParameterException NOT thrown from Timecard(int[]) with null parameters");
		} catch (NullParameterException e) {
			assertEquals(e.getMessage(), "Null int array passed to Timecard c'tor");
		} catch (BadParameterException e) {
			fail("Wrong Exception (BadParameterException) thrown from Timecard(int[]) with null parameters");
		}

		testDaysIn = new int[] { 5, 6, 7, 8, 9, 10 };
		try {
			testTimecard = new Timecard(testDaysIn);
			fail("BadParameterException NOT thrown from Timecard(int[]) with array bigger than NUM_DAYS");
		} catch (NullParameterException e) {
			fail("Wrong Exception (NullParameterException) thrown from Timecard(int[]) with array bigger than NUM_DAYS");
		} catch (BadParameterException e) {
			assertTrue(e.getMessage().startsWith("Invalid int array passed to Timecard c'tor"));
		}

		testDaysIn = new int[] { 5, 6, 7, 8 };
		try {
			testTimecard = new Timecard(testDaysIn);
			fail("BadParameterException NOT thrown from Timecard(int[]) with array smaller than NUM_DAYS");
		} catch (NullParameterException e) {
			fail("Wrong Exception (NullParameterException) thrown from Timecard(int[]) with array smaller than NUM_DAYS");
		} catch (BadParameterException e) {
			assertTrue(e.getMessage().startsWith("Invalid int array passed to Timecard c'tor"));
		}

		testDaysIn = new int[] { -1, 6, 7, 8, 9 };
		try {
			testTimecard = new Timecard(testDaysIn);
			fail("BadParameterException NOT thrown from Timecard(int[]) with less than zero hours");
		} catch (NullParameterException e) {
			fail("Wrong Exception (NullParameterException) thrown from Timecard(int[]) with less than zero hours");
		} catch (BadParameterException e) {
			assertTrue(e.getMessage().startsWith("Bad hours value passed to setHoursByDay"));
		}

		testDaysIn = new int[] { 5, 6, 7, 8, 25 };
		try {
			testTimecard = new Timecard(testDaysIn);
			fail("BadParameterException NOT thrown from Timecard(int[]) with higher than 24 hours value");
		} catch (NullParameterException e) {
			fail("Wrong Exception (NullParameterException) thrown from Timecard(int[]) with higher than 24 hours value");
		} catch (BadParameterException e) {
			assertTrue(e.getMessage().startsWith("Bad hours value passed to setHoursByDay"));
		}
	}

	@Test
	public void testTimecardTimecard() {
		try {
			Timecard testTimecard2 = new Timecard(testTimecard);
			assertEquals(testTimecard.toString(), testTimecard2.toString());
		} catch (BadParameterException e) {
			fail("BadParameterException (" + e.getMessage() + ") thrown from Timecard(Timecard) with valid parameters");
		}
	}

	@Test
	public void testGetWeeklyHours() throws NullParameterException, BadParameterException {
		testDaysIn = new int[] { 5, 5, 5, 5, 5 };
		testTimecard = new Timecard(testDaysIn);
		assertEquals(25, testTimecard.getWeeklyHours());
	}

	@Test
	public void testGetHoursByDay() throws NullParameterException, BadParameterException {
		testTimecard = new Timecard(new int[] { 1, 2, 3, 4, 5 });
		try {
			assertEquals(3, testTimecard.getHoursByDay(2));
		} catch (BadParameterException e) {
			fail("BadParameterException (" + e.getMessage() + ") thrown from getHoursByDay(int) with valid parameters");
		}

		try {
			testTimecard.getHoursByDay(-1);
			fail("BadParameterException NOT thrown from getHoursByDay(int) with day < 0");
		} catch (BadParameterException e) {
			assertTrue(e.getMessage().startsWith("Bad day value passed to getHoursByDay: "));
		}

		try {
			testTimecard.getHoursByDay(Timecard.NUM_DAYS);
			fail("BadParameterException NOT thrown from getHoursByDay(int) with day = NUM_DAYS");
		} catch (BadParameterException e) {
			assertTrue(e.getMessage().startsWith("Bad day value passed to getHoursByDay: "));
		}

	}

	@Test
	public void testToString() {
		assertTrue(testTimecard.toString().contains("Weekly Hours:"));
	}

}
