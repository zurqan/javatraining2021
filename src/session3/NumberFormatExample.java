package session3;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatExample {

    public static void main(String[] args) {
        double amount = 987654523.23467;
        NumberFormat nf = NumberFormat.getCurrencyInstance();

        String formattedAmount = nf.format(amount);
        System.out.println("formattedAmount = " + formattedAmount);

        String numberFormattedClassName = nf.getClass().getSimpleName();
        System.out.println("numberFormattedClassName = " + numberFormattedClassName);


        NumberFormat nfChina = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        String chinaAmount = nfChina.format(amount);
        System.out.println("chinaAmount = " + chinaAmount);


    }

    public void methodName(){

    }
}
