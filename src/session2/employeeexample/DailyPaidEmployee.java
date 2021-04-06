package session2.employeeexample;

import java.time.LocalDate;

public class DailyPaidEmployee extends Employee {

    private double hourRate;

    private double hoursPerDay;

    public DailyPaidEmployee(String name, LocalDate hireDate, double hourRate, double hoursPerDay) {
        super(name, hireDate);
        this.hourRate = hourRate;
        this.hoursPerDay = hoursPerDay;
    }

    @Override
    public double getSalaryPerMonth() {
        return hourRate*hoursPerDay*22;
    }
}
