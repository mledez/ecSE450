
package domain;

import java.util.ArrayList;
import java.util.Collections;

import utils.BadParameterException;
import utils.NullParameterException;

/**
 * The Department class represents a Department that could include Employees.<br>
 * <br>
 * Usage example:
 * 
 * <pre>
 * Department myDepartment = new DePartment("Advising");
 * </pre>
 * 
 * @author Christopher Hield
 * @see domain.Employee
 * @see domain.SalesEmployee
 * @see domain.Timecard
 * @since Version 1.8
 *
 */
public class Department {

	/**
	 * The current name of the Department.
	 * 
	 * @see #getDepartmentName()
	 * @see #setDepartmentName(String)
	 */
	private String departmentName;

	/**
	 * The maximum number of Employees that will be accepted ({@value})
	 */
	public static final int MAX_EMP = 20;

	/**
	 * The ArrayList employeeList represents the Department's employee list.
	 * 
	 * @see #getEmployeeList()
	 * @see #addEmployee(Employee)
	 * @see #addEmployee(SalesEmployee)
	 * @see #removeEmployee(int)
	 */
	private final ArrayList<Employee> employeeList = new ArrayList<>(0);

	/**
	 * Constructor for the Department - needs a non-null and non-empty "String" name parameter.
	 * 
	 * @param dName
	 *            The department's name
	 * @throws NullParameterException
	 *             If the name parameter is null
	 * @throws BadParameterException
	 *             If the name parameter is an empty String
	 * @see #setDepartmentName(String)
	 */
	public Department(String dName) throws NullParameterException, BadParameterException {
		setDepartmentName(dName);
	}

	/**
	 * Returns the name of the Department.
	 * 
	 * @see #setDepartmentName(String)
	 * @return Returns the name of the Department
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * Sets the provided name as the name of the Department.
	 * 
	 * @param dNameIn
	 *            The Department's name
	 * @throws NullParameterException
	 *             If the name parameter is null
	 * @throws BadParameterException
	 *             If the name parameter is an empty String
	 * @see #departmentName
	 */
	private void setDepartmentName(String dNameIn) throws NullParameterException, BadParameterException {
		if (dNameIn == null) {
			throw new NullParameterException("Null value passed in for departmentName");
		}

		if (dNameIn.isEmpty()) {
			throw new BadParameterException("Invalid Department Name: " + dNameIn);
		}
		departmentName = dNameIn;
	}

	/**
	 * Returns the Department's list of Employees.
	 * 
	 * @return Returns the Department's list of Employees
	 */
	private ArrayList<Employee> getEmployeeList() {
		return employeeList;
	}

	/**
	 * Adds the provided Employee to the Department's employee list.
	 * 
	 * @param e
	 *            The Employee to be added
	 * @throws NullParameterException
	 *             If the Employee is null
	 * @throws BadParameterException
	 *             If the Department has no room to add the Employee
	 * @see #addEmployee(SalesEmployee)
	 * @see #removeEmployee(int)
	 * @see #isInDepartment(int)
	 */
	public void addEmployee(Employee e) throws NullParameterException, BadParameterException {
		if (e == null) {
			throw new NullParameterException("Null Employee sent to addEmployee!");
		}
		if (getEmployeeList().size() >= MAX_EMP) {
			throw new BadParameterException("This Department is already at the max. number of employees: " + MAX_EMP);
		}
		getEmployeeList().add(new Employee(e));
		Collections.sort(getEmployeeList());
	}

	/**
	 * Adds the provided Sales Employee to the Department's employee list.
	 * 
	 * @param e
	 *            The Sales Employee to be added
	 * @throws NullParameterException
	 *             If the Sales Employee is null
	 * @throws BadParameterException
	 *             If the Department has no room to add the Sales Employee
	 * @see #addEmployee(Employee)
	 * @see #removeEmployee(int)
	 * @see #isInDepartment(int)
	 */
	public void addEmployee(SalesEmployee e) throws NullParameterException, BadParameterException {
		if (e == null) {
			throw new NullParameterException("Null Employee sent to addEmployee!");
		}
		if (getEmployeeList().size() >= MAX_EMP) {
			throw new BadParameterException("This Department is already at the max. number of employees: " + MAX_EMP);
		}

		SalesEmployee ee = new SalesEmployee(e); // Create a SalesEmployee copy
		getEmployeeList().add(ee);
		Collections.sort(getEmployeeList());

	}

	/**
	 * Removes the Employee matching the provided Employee-Id from the Department's Employee List.
	 * 
	 * @param id
	 *            The Employee ID of the Employee to be removed from the Employee list
	 * @return Returns the removed Employee or null if Employee not found
	 * @see #addEmployee(Employee)
	 * @see #addEmployee(SalesEmployee)
	 * @see #isInDepartment(int)
	 */
	public Employee removeEmployee(int id) {
		for (Employee e : getEmployeeList()) {
			if (e.getEmployeeId() == id) {
				Employee emp = e;
				getEmployeeList().remove(e);
				return emp;
			}
		}
		return null;
	}

	/**
	 * Checks whether or not an Employee with the provided ID is currently in the Department.
	 * 
	 * @param id
	 *            The Employee ID of the Employee to be checked
	 * @return Returns True if the Employee is in the Department, False if not
	 * @see #addEmployee(Employee)
	 * @see #addEmployee(SalesEmployee)
	 * @see #removeEmployee(int)
	 */
	public boolean isInDepartment(int id) {
		for (Employee e : getEmployeeList()) {
			if (e.getEmployeeId() == id) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the number of Employees currently in the Department.
	 * 
	 * @return Returns the number of Employees currently in the Department
	 * 
	 */
	public int getNumInDepartment() {
		return getEmployeeList().size();
	}

	/**
	 * Returns a report with all the information about the department. It includes Department's name, and Employee's information.
	 * 
	 * @return Returns a report with all the information about the department. It includes Department's name, and Employee's information
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Department: %s\n", getDepartmentName()));
		for (int i = 0; i < ("Department: " + getDepartmentName()).length(); i++) {
			sb.append("-");
		}
		sb.append("\nEmployees:\n");
		for (Employee e : getEmployeeList()) {
			sb.append(e + "\n");
		}

		return sb.toString();

	}
}
