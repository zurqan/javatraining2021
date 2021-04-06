package session2.employeeexample;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Employee implements Comparable<Employee>{

    private String name;

    private LocalDate hireDate;

    public static final String DEFAULT_NAME="UNKNOWN";

    public Employee() {
        this(DEFAULT_NAME);
    }

    public Employee(String name) {
        this(name,LocalDate.now());
    }

    public Employee(String name, LocalDate hireDate) {
        this.name = name;
        this.hireDate = hireDate;
    }

    public abstract double getSalaryPerMonth();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", hireDate=" + hireDate +
                '}';
    }

    @Override
    public int compareTo(Employee anotherEmployee) {
        return name.compareTo(anotherEmployee.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getName(), employee.getName()) &&
                Objects.equals(getHireDate(), employee.getHireDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getHireDate());
    }
}
