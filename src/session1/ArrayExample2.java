package session1;

import java.util.Arrays;

public class ArrayExample2 {

    public static void main(String[] args) {
        int[] array=new int[]{1,2,4,5};
//        printMe(array);
//        printMe(new int[]{1,2,3,4,5});

        int[] anotherArray = Arrays.copyOf(array, 6);
//        printMe(anotherArray);
        anotherArray[0]=10000;
        printMe(anotherArray);
        System.out.println("Old Array");
        printMe(array);//

        int[][] twoDimArray=new int[][]{{1,2},{1,2,3,4},{100,300,400}};
        int[] firstRow = twoDimArray[0];
        printMe(firstRow);
    }

    public static void printMe(int[] array){
        for (int i : array) {
            System.out.println(i);
        }
    }
}
