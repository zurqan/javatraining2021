package session2.shapeexample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortingExample {

    public static void main(String[] args) {
        Shape s1=new Square(10);
        Shape s2= new Circle(10);
        Shape s3 = new Rectangle(10,20);

        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(s1);
        shapes.add(s2);
        shapes.add(s3);
//        Collections.sort(shapes);


        ArrayList<Integer> numbers=new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(4);
        numbers.add(3);

        System.out.println("numbers = " + numbers);
        Collections.sort(numbers);
        System.out.println("numbers = " + numbers);
    }

}
