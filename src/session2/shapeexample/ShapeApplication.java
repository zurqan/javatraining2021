package session2.shapeexample;


public class ShapeApplication {

    public static void main(String[] args) {

        Shape s1=new Square(10);
        Shape s2= new Circle(10);
        Shape s3 = new Rectangle(10,20);


        printArea(s1);
        printArea(s2);
        printArea(s3);
        printArea(new Square(10));
    }

    private static void printArea(Shape s1) {
        System.out.println("Area = " + s1.getArea());
    }
//    private static void printArea(Square s1) {
//        System.out.println("Area ?= " + s1.getArea());
//    }
//    private static void printArea(Circle s1) {
//        System.out.println("Area = " + s1.getArea());
//    }
//    private static void printArea(Rectangle s1) {
//
//        System.out.println("Area = " + s1.getArea());
//    }
}
