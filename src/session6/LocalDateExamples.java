package session6;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalDateExamples {


    public static void main(String[] args) {

        LocalDate now = LocalDate.now();
        System.out.println("now = " + now);

        LocalTime nowT = LocalTime.now();
        System.out.println("nowT = " + nowT);

        LocalDateTime dateTimeNow = LocalDateTime.now();
        System.out.println("dateTimeNow = " + dateTimeNow);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        String formattedDateTime = dateTimeNow.format(formatter);
        System.out.println("formattedDateTime = " + formattedDateTime);

        LocalDateTime localDateTime = dateTimeNow.withDayOfMonth(2).withMonth(1);
        System.out.println("localDateTime = " + localDateTime);

    }
}
