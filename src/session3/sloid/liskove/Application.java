package session3.sloid.liskove;

public class Application {


    public static void main(String[] args) {
        Rectangle r1= new Rectangle();

        r1.setHeight(10);
        r1.setWidth(15);

        int area1 = r1.calculateArea();
        System.out.println("area1 = " + area1);

        assert area1 == 150:"Area is not accurate.. it should be 150";


        Rectangle s1=new Square();
        s1.setWidth(10);
        int squareArea = s1.calculateArea();
        System.out.println("squareArea = " + squareArea);

        assert squareArea==100;


        s1.setWidth(10);
        s1.setHeight(15);
        int area2 = s1.calculateArea();
        System.out.println("area2 = " + area2);
        assert area2==150;
    }
}
