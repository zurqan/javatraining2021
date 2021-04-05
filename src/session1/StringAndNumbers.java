package session1;

public class StringAndNumbers {

    public static void main(String[] args) {

        //converting from number to String
        int a =10;
        String n = "" + a;
        String number = "The number is : " + a;
        System.out.println("number = " + number);

        String ten = String.valueOf(10);
        System.out.println("ten = " + ten);

        //converting String to number
        String tenStr= "10";
        System.out.println(tenStr+1);
        int tenInt = Integer.valueOf(tenStr).intValue();
        System.out.println("tenInt = " + tenInt);
        System.out.println(tenInt + 1);
        System.out.println("tenInt+1 = " + (tenInt + 1));
        System.out.println("tenInt+1 = " + tenInt + 1);
    }

}
