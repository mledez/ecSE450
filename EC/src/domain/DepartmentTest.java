package domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utils.BadParameterException;
import utils.NullParameterException;

public class DepartmentTest {

	private String testName;
	private Department testDepartment;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {}

	@Before
	public void setUp() {
		try {
			testName = "testDepartment";
			testDepartment = new Department(testName);
		} catch (NullParameterException | BadParameterException e) {
			fail("Creation of test department object in @Before failed: " + e.getMessage());
		}
	}

	@After
	public void tearDown() throws Exception {}

	@Test
	public void testDepartment() {
		testName = "testDepartment2";
		try {
			testDepartment = new Department(testName);
			assertEquals(testName, testDepartment.getDepartmentName());
		} catch (NullParameterException e) {
			fail("NullParameterException (" + e.getMessage() + ") thrown from Department(name) with a valid name");
		} catch (BadParameterException e) {
			fail("BadParameterException (" + e.getMessage() + ") thrown from Department(name) with a valid name");
		}

		testName = null;
		try {
			testDepartment = new Department(testName);
			fail("NullParameterException NOT thrown from Department(name) with a Null Parameter");
		} catch (NullParameterException e) {
			assertEquals(e.getMessage(), "Null value passed in for departmentName");
			assertTrue(!testDepartment.getDepartmentName().equals(testName));
		} catch (BadParameterException e) {
			fail("Wrong Exception (BadParameterException) thrown from Department(name) with a Null Parameter");
		}

		testName = "";
		try {
			testDepartment = new Department(testName);
			fail("BadParamenterException NOT thrown from Department(name) with an empty Parameter");
		} catch (NullParameterException e) {
			fail("Wrong Exception (NullParameterException) thrown from Department(name) with an empty Parameter");
		} catch (BadParameterException e) {
			assertTrue(e.getMessage().startsWith("Invalid Department Name"));
			assertTrue(!testDepartment.getDepartmentName().equals(testName));
		}
	}

	@Test
	public void testGetDepartmentName() {
		String name = testDepartment.getDepartmentName();
		assertNotNull(name);
		assertFalse(name.isEmpty());
		assertEquals(testName, name);
	}

	@Test
	public void testAddEmployeeEmployee() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddEmployeeSalesEmployee() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveEmployee() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsInDepartment() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNumInDepartment() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
