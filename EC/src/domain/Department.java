
package domain;

import java.util.ArrayList;
import java.util.Collections;

import utils.BadParameterException;
import utils.NullParameterException;

public class Department {

	private String departmentName;
	public static final int MAX_EMP = 20;
	private final ArrayList<Employee> employeeList = new ArrayList<>(0);

	public Department(String dName) throws NullParameterException, BadParameterException {
		setDepartmentName(dName);
	}

	public String getDepartmentName() {
		return departmentName;
	}

	private void setDepartmentName(String dNameIn) throws NullParameterException, BadParameterException {
		if (dNameIn == null) {
			throw new NullParameterException("Null value passed in for departmentName");
		}

		if (dNameIn.isEmpty()) {
			throw new BadParameterException("Invalid Department Name: " + dNameIn);
		}
		departmentName = dNameIn;
	}

	private ArrayList<Employee> getEmployeeList() {
		return employeeList;
	}

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

	public boolean isInDepartment(int id) {
		for (Employee e : getEmployeeList()) {
			if (e.getEmployeeId() == id) {
				return true;
			}
		}
		return false;
	}

	public int getNumInDepartment() {
		return getEmployeeList().size();
	}

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
