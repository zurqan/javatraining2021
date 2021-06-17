package session10;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FailFastAndSafeExample {

    public static void main(String[] args) {

//        List<String> names = new ArrayList<>();
        List<String> names = new CopyOnWriteArrayList<>();
        names.add("Mohammad");
        names.add("Ahmad");
        names.add("Mosa");
        names.add("Esa");

        Iterator<String> iterator = names.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            System.out.println("name = " + name);
            names.remove("Mosa");

            System.out.println("names = " + names);
        }

    }
}
