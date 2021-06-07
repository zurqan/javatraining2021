package session6;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

public class TemproalExample {

    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime with = now.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("with = " + with);
    }
}
