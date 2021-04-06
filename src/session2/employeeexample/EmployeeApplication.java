package session2.employeeexample;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeApplication {

    public static void main(String[] args) {
        Employee e1 = new SalariedEmployee();
        Employee e2 = new DailyPaidEmployee("Ahmad", LocalDate.now(),10,4);
        Employee e3 = new SalariedEmployee("Mohammad",LocalDate.now(),550);

        List<Employee> listOfEmployees = new ArrayList<>();
        listOfEmployees.add(e1);
        listOfEmployees.add(e2);
        listOfEmployees.add(e3);

        double totalAmount=calculateNetSalaries(listOfEmployees);

        System.out.println("e1.getSalaryPerMonth() = " + e1.getSalaryPerMonth());
        System.out.println("e2.getSalaryPerMonth() = " + e2.getSalaryPerMonth());
        System.out.println("e3.getSalaryPerMonth() = " + e3.getSalaryPerMonth());

        System.out.println("e1.getName() = " + e1.getName());
        System.out.println("totalAmount = " + totalAmount);


        System.out.println("listOfEmployees = " + listOfEmployees);

        Collections.sort(listOfEmployees);
        System.out.println("After Sorting");
        System.out.println("listOfEmployees = " + listOfEmployees);

    }

    private static double calculateNetSalaries(List<Employee> employees) {

        double sum =0;
        for (Employee employee : employees) {
            sum+=employee.getSalaryPerMonth();
        }
        return sum;
    }
}
