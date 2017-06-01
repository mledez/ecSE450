package test;

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

import domain.Department;
import domain.Employee;
import domain.SalesEmployee;
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
			assertFalse(testDepartment.getDepartmentName().equals(testName));
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
			assertFalse(testDepartment.getDepartmentName().equals(testName));
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
		Employee testEmployee;
		int firstId = 1234;
		try {
			testEmployee = new Employee("Miguel", "Ledezma", firstId, 99.99, new int[] { 5, 6, 7, 8, 9 });
			testDepartment.addEmployee(testEmployee);
			assertTrue(testDepartment.isInDepartment(1234));
		} catch (NullParameterException e) {
			fail("NullParameterException (" + e.getMessage()
					+ ") thrown from AddEmployee(Employee) with valid parameters");
		} catch (BadParameterException e) {
			fail("BadParameterException (" + e.getMessage()
					+ ") thrown from AddEmployee(Employee) with valid parameters");
		}

		testEmployee = null;
		int employeeNum = testDepartment.getNumInDepartment();
		try {
			testDepartment.addEmployee(testEmployee);
			fail("NullParameterException NOT thrown from AddEmployee(Employee) with a Null Parameter");
		} catch (NullParameterException e) {
			assertEquals(e.getMessage(), "Null Employee sent to addEmployee!");
			assertEquals(testDepartment.getNumInDepartment(), employeeNum);
		} catch (BadParameterException e) {
			fail("Wrong Exception (BadParameterException) thrown from AddEmployee(Employee) with a Null Parameter");
		}

		try {
			for (int i = testDepartment.getNumInDepartment(); i <= Department.MAX_EMP; i++) {
				firstId++;
				testDepartment
						.addEmployee(new Employee("Miguel", "Ledezma", firstId, 99.99, new int[] { 5, 6, 7, 8, 9 }));
			}
			assertEquals(testDepartment.getNumInDepartment(), Department.MAX_EMP);
			firstId++;
			testDepartment.addEmployee(new Employee("Miguel", "Ledezma", firstId, 99.99, new int[] { 5, 6, 7, 8, 9 }));
			fail("BadParamenterException NOT thrown from AddEmployee(Employee) with department already at max");
		} catch (NullParameterException e) {
			fail("Wrong Exception (NullParameterException) thrown from AddEmployee(Employee) when adding with department already at max");
		} catch (BadParameterException e) {
			assertTrue(e.getMessage().startsWith("This Department is already at the max"));
			assertEquals(testDepartment.getNumInDepartment(), Department.MAX_EMP);
		}
	}

	@Test
	public void testAddEmployeeSalesEmployee() {
		SalesEmployee testSalesEmployee;
		int firstId = 1234;
		try {
			testSalesEmployee = new SalesEmployee("Miguel", "Ledezma", firstId, 99.99, new int[] { 5, 6, 7, 8, 9 },
					10000, 12000);
			testDepartment.addEmployee(testSalesEmployee);
			assertTrue(testDepartment.isInDepartment(1234));
		} catch (NullParameterException e) {
			fail("NullParameterException (" + e.getMessage()
					+ ") thrown from AddEmployee(SalesEmployee) with valid parameters");
		} catch (BadParameterException e) {
			fail("BadParameterException (" + e.getMessage()
					+ ") thrown from AddEmployee(SalesEmployee) with valid parameters");
		}

		testSalesEmployee = null;
		int employeeNum = testDepartment.getNumInDepartment();
		try {
			testDepartment.addEmployee(testSalesEmployee);
			fail("NullParameterException NOT thrown from AddEmployee(SalesEmployee) with a Null Parameter");
		} catch (NullParameterException e) {
			assertEquals(e.getMessage(), "Null Employee sent to addEmployee!");
			assertEquals(testDepartment.getNumInDepartment(), employeeNum);
		} catch (BadParameterException e) {
			fail("Wrong Exception (BadParameterException) thrown from AddEmployee(SalesEmployee) with a Null Parameter");
		}

		try {
			for (int i = testDepartment.getNumInDepartment(); i <= Department.MAX_EMP; i++) {
				firstId++;
				testDepartment.addEmployee(new SalesEmployee("Miguel", "Ledezma", firstId, 99.99,
						new int[] { 5, 6, 7, 8, 9 }, 10000, 12000));
			}
			System.out.println(testDepartment.getNumInDepartment());
			assertEquals(testDepartment.getNumInDepartment(), Department.MAX_EMP);
			firstId++;
			testDepartment.addEmployee(
					new SalesEmployee("Miguel", "Ledezma", firstId, 99.99, new int[] { 5, 6, 7, 8, 9 }, 10000, 12000));
			fail("BadParamenterException NOT thrown from AddEmployee(SalesEmployee) with department already at max");
		} catch (NullParameterException e) {
			fail("Wrong Exception (NullParameterException) thrown from AddEmployee(SalesEmployee) when adding with department already at max");
		} catch (BadParameterException e) {
			assertTrue(e.getMessage().startsWith("This Department is already at the max"));
			assertEquals(testDepartment.getNumInDepartment(), Department.MAX_EMP);
		}
	}

	@Test
	public void testRemoveEmployee() throws NullParameterException, BadParameterException {
		int employeeId = 1234;
		Employee testEmployee = new Employee("Miguel", "Ledezma", employeeId, 99.99, new int[] { 5, 6, 7, 8, 9 });
		testDepartment.addEmployee(testEmployee);
		assertEquals(testEmployee.getEmployeeId(), testDepartment.removeEmployee(1234).getEmployeeId());

		employeeId = -1;
		assertTrue(testDepartment.removeEmployee(employeeId) == null);
	}

	@Test
	public void testIsInDepartment() throws NullParameterException, BadParameterException {
		int employeeId = 1234;
		Employee testEmployee = new Employee("Miguel", "Ledezma", employeeId, 99.99, new int[] { 5, 6, 7, 8, 9 });
		testDepartment.addEmployee(testEmployee);

		assertTrue(testDepartment.isInDepartment(employeeId));

		employeeId = -1;
		assertFalse(testDepartment.isInDepartment(employeeId));
	}

	@Test
	public void testGetNumInDepartment() throws NullParameterException, BadParameterException {
		assertTrue(testDepartment.getNumInDepartment() == 0);
		int id = 1234;
		for (int i = 1; i <= Department.MAX_EMP; i++) {
			testDepartment.addEmployee(
					new SalesEmployee("Miguel", "Ledezma", id, 99.99, new int[] { 5, 6, 7, 8, 9 }, 10000, 12000));
			id++;
			assertTrue(testDepartment.getNumInDepartment() == i);
		}
	}

	@Test
	public void testToString() {
		assertTrue(testDepartment.toString().startsWith("Department: "));
	}

}
