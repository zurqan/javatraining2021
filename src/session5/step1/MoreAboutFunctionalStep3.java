package session5.step1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MoreAboutFunctionalStep3 {

    public static void main(String[] args) {
        //()->a
        MySupplier<Integer> initialValue = ()->10;
        Integer integer = initialValue.get();
        System.out.println("integer = " + integer);

        MySupplier<List<Integer>> listSupplier = ()->new ArrayList<>();
        MySupplier<List<Integer>> listSupplier2 = ()->new LinkedList<>();

        System.out.println("listSupplier.get() = " + listSupplier.get());
    }
}
