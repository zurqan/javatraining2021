package session10;

import java.util.EnumSet;

public class EnumSetExample {

    public static enum Color{
        RED,//0
        BLACK,//1
        WHITE,//2
        BLUE,//3
        YELLOW//4
    }
    public static void main(String[] args) {

        EnumSet<Color>  colors = EnumSet.of(Color.RED);
        System.out.println("colors = " + colors);
        EnumSet<Color> allColors = EnumSet.allOf(Color.class);
        System.out.println("allColors = " + allColors);
        EnumSet<Color> fromBlackToBlue = EnumSet.range(Color.BLACK, Color.BLUE);
        System.out.println("fromBlackToBlue = " + fromBlackToBlue);
        System.out.println("EnumSet.complementOf(fromBlackToBlue) = " + EnumSet.complementOf(fromBlackToBlue));

//        System.out.println(fromBlackToBlue.add(null));

        //EnumSet

    }
}
