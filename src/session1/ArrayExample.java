package session1;

import java.util.Scanner;

public class ArrayExample {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Please enter array Size: ");
        int arraySize= sc.nextInt();
        int[] array1 = new int[arraySize];
//value will be initialized with zero
/*        for (int i = 0; i < array1.length; i++) {
            System.out.println("i: "+i+", Value: "+array1[i]);
        }*/
        for (int i = 0; i < array1.length; i++) {
            array1[i] = i;

        }
        for (int i = 0; i < array1.length; i++) {
            System.out.println("i: " + i + ", Value: " + array1[i]);
        }

        for (int i = array1.length - 1; i >= 0; i--) {
            System.out.println("rev -i = " + i);
        }

        for (int i : array1) {
            System.out.println("i = " + i);
        }
    }
}
