package session10;

import session5.step1.CBiFunction;

import java.util.function.Function;
import java.util.function.Predicate;

public class Str extends FList<Character> {

    private final FList<Character> chars;

    public Str(FList<Character> chars) {
        this.chars = chars;
    }

    public Str(String str) {
        this(str.chars().mapToObj(c->(char)c).toArray(Character[]::new));
    }

    public Str(Character[] toCharArray) {
        this(FList.of(toCharArray));
    }

    @Override
    public Character head() {
        return chars.head();
    }

    @Override
    public FList<Character> tail() {
        return chars.tail();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public FList<Character> set(Character head) {
        return chars.set(head);
    }

    @Override
    public FList<Character> drop(int n) {
        return chars.drop(n);
    }

    @Override
    public FList<Character> dropWhile(Predicate<Character> condition) {
        return chars.dropWhile(condition);
    }

    @Override
    public FList<Character> reverse() {
        return chars.reverse();
    }

    @Override
    public FList<Character> init() {
        return chars.init();
    }

    @Override
    public int size() {
        return chars.size();
    }

    @Override
    public <U> U reduceL(U seed, CBiFunction<U, Character, U> reduceFunction) {

        return chars.reduceL(seed,reduceFunction);
    }

    @Override
    public <U> U reduceR(U seed, CBiFunction<Character, U, U> reduceFunction) {
        return chars.reduceR(seed,reduceFunction);
    }

    @Override
    public <U> FList<U> map(Function<Character, U> mapFunc) {
        return chars.map(mapFunc);
    }

    @Override
    public <U> FList<U> flatMap(Function<Character, FList<U>> flatMapFunc) {
        return chars.flatMap(flatMapFunc);
    }

    @Override
    public FList<Character> filter(Predicate<Character> condition) {
        return chars.filter(condition);
    }

    @Override
    public String toString() {
        return chars.toString();
    }

    public Str concat(Str another){
        return new Str(concat(this,another));
    }

    public boolean contains(Str another){
        return hasSubList(this, another);
    }

    public boolean startsWith(Str another){
        return startsWith(this, another);
    }

    public static Str str(String value){
        return new Str(value);
    }


    public static void main(String[] args) {
        Str test = str("test");
        System.out.println("test = " + test);
        System.out.println("test.head() = " + test.head());

        System.out.println("test.concat(str(\" another\")) = " + test.concat(str(" another")));

        System.out.println(test.reverse());

        System.out.println("test.contains(str(\"et\")) = " + test.contains(str("et")));
        System.out.println("test.contains(str(\"st\")) = " + test.contains(str("st")));
    }
}
