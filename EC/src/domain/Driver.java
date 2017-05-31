package domain;

import utils.BadParameterException;
import utils.NullParameterException;

public class Driver {

    public static void main(String[] args) {

        Department d;
        try {
            d = new Department("Marketing");

            Employee e1 = new Employee("Alan", "Conte", 6642, 35.75, new int[]{7, 6, 7, 8, 8});
            d.addEmployee(e1);

            Employee e2 = new Employee("Gina", "Thomas", 1547, 37.87, new int[]{8, 8, 8, 8, 7});
            d.addEmployee(e2);

            SalesEmployee e3 = new SalesEmployee("Raj", "Pandy", 5489, 36.90, new int[]{6, 9, 7, 8, 8}, 10000, 12250);
            d.addEmployee(e3);

            SalesEmployee e4 = new SalesEmployee("Angel", "Ramirez", 7845, 37.50, new int[]{6, 9, 7, 8, 8}, 12000, 9950);
            d.addEmployee(e4);

            System.out.println(d);

        } catch (BadParameterException | NullParameterException ex) {
            System.err.println(ex);
        }

    }

}
