package session5.step1;

@FunctionalInterface
public interface MyPredicate<T> {

    public boolean test(T t);

    default MyPredicate<T> and(MyPredicate<T> another){

        return a->test(a)&&another.test(a);
    }
    default MyPredicate<T> or(MyPredicate<T> another){

        return a->test(a)||another.test(a);
    }
    default MyPredicate<T> neg(){

        return a->!test(a);
    }


}

//public interface MyPredicate<T> extends Function<T,Boolean> {
//
//}
