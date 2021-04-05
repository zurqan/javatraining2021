package session1;

import java.util.Scanner;

public class ScannerExample {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        int input1 = scanner.nextInt();
//        System.out.println("input1 = " + input1);

        System.out.print("please enter your name: ");
        String name = scanner.nextLine();
        System.out.println("\nHello " + name);
        System.out.print("\nplease enter your age: ");
        int age = scanner.nextInt();
        System.out.println("\nYour age is: " + age);

    }
}
