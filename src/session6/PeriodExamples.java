package session6;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class PeriodExamples {

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();

        LocalDate future = LocalDate.of(2021, Month.DECEMBER, 12);

        Period between = Period.between(now, future);
        System.out.println("between = " + between);


        LocalDateTime plus = LocalDateTime.now().plus(Duration.of(1, ChronoUnit.HOURS));
        System.out.println("plus = " + plus);

    }
}
