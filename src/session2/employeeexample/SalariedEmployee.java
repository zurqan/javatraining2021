package session2.employeeexample;

import java.time.LocalDate;

public class SalariedEmployee extends Employee {

    private double salary;

    public static final double DEFAULT_SALARY =280;

    public SalariedEmployee() {
        this.salary=DEFAULT_SALARY;
    }

    public SalariedEmployee(String name, LocalDate hireDate, double salary) {
        super(name, hireDate);
        this.salary = salary;
    }



    @Override
    public double getSalaryPerMonth() {
        return salary;//this.salary
    }
}
