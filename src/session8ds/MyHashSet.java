package session8ds;

import session5.step2.Student;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static session5.step2.Course.*;
import static session5.step2.Gender.FEMALE;
import static session5.step2.Gender.MALE;

public class MyHashSet<E> {

    final static int DEFAULT_SIZE = 10;
    final Object[] data;
    int size;

    public MyHashSet(int arraySize) {
        this.data = new Object[arraySize];
    }

    public MyHashSet() {
        this(DEFAULT_SIZE);
    }

    public boolean contains(Object e){
        int position = getPosition(e);
        MyLinkedList<E> bucket = (MyLinkedList)data[position];

        return bucket!=null&&bucket.anyMatch(Predicate.isEqual(e));

    }

    public MyHashSet<E> add(E e){
        int position = getPosition(e);
        MyLinkedList<E> bucket = (MyLinkedList)data[position];

        if(bucket==null){
            bucket=new MyLinkedList<>();
            bucket.addLast(e);
            data[position]=bucket;
            size++;
            return this;
        }

        //(element==null && e==null) e == element and e == null
//        boolean founded = bucket.anyMatch(element -> Objects.equals(e,element));
        boolean founded = bucket.anyMatch(Predicate.isEqual(e));
        if(founded){
            return this;
        }
        bucket.addLast(e);
        size++;
        return this;
    }

    public void debug(){
        for (int i = 0; i < data.length; i++) {

            System.out.format(" %d ) %s\n",i,data[i]);
        }
    }

    private int getPosition(Object e) {
        if(e==null)return 0;
        int hashCode = Math.abs(e.hashCode());
        int position = hashCode%data.length;
        return position;
    }

    public static void main(String[] args) {
//        MyHashSet<String> hashSet = new MyHashSet<>();
//        hashSet.debug();
//
//        traceAdd(hashSet,"Test");
//        traceAdd(hashSet,"abc");
//        traceAdd(hashSet,"Ahmad");
//        traceAdd(hashSet,"Mohammad");
//        traceAdd(hashSet,"Mosa");
//        traceAdd(hashSet,"Mosa");
//        traceAdd(hashSet,"Esa");
//        traceAdd(hashSet,"Ruba");
//        traceAdd(hashSet,null);
//        traceAdd(hashSet,null);


        Student[] students = new Student[]{
                new Student("Ahmad", 25, MALE, false, asList(MATH, JAVA, ARABIC)),

                new Student("Ahmad", 25, MALE, true, asList(MATH, JAVA, ARABIC)),
                new Student("Mosa", 20, MALE, true, asList(ENGLISH, ARABIC)),
                new Student("Ahmad", 25, MALE, false, asList(MATH, JAVA, ARABIC)),
                new Student("Ahmad", 25, MALE, true, asList(MATH, JAVA, ARABIC)),
                new Student("Ahmad", 25, MALE, true, asList(MATH, JAVA, ARABIC)),
                new Student("Ahmad", 25, MALE, true, asList(MATH, JAVA, ARABIC)),
                new Student("Ahmad", 25, MALE, true, asList(MATH, JAVA, ARABIC)),
                new Student("Ahmad", 25, MALE, false, asList(MATH, JAVA, ARABIC)),

                new Student("Mohammad", 31, MALE, false, asList(MATH, ARABIC)),
                new Student("Mosa", 20, MALE, true, asList(ENGLISH, ARABIC)),
                new Student("Heba", 20, FEMALE, false, asList(ENGLISH, CHEMISTRY)),
                new Student("Ruba", 30, FEMALE, true, asList(JAVA, MATH, ENGLISH, CHEMISTRY)),
                new Student("Esa", 45, MALE, false, asList(JAVA, MATH, ENGLISH, CHEMISTRY)),

                new Student("Mohammad", 31, MALE, true, asList(MATH, ARABIC)),
                new Student("Mosa", 20, MALE, false, asList(ENGLISH, ARABIC)),
                new Student("Heba", 20, FEMALE, true, asList(ENGLISH, CHEMISTRY)),
                new Student("Ruba", 30, FEMALE, false, asList(JAVA, MATH, ENGLISH, CHEMISTRY)),
                new Student("Esa", 45, MALE, true, asList(JAVA, MATH, ENGLISH, CHEMISTRY))
                ,


                new Student("Mohammad", 31, MALE, true, asList(MATH, ARABIC)),
                new Student("Mosa", 20, MALE, false, asList(ENGLISH, ARABIC)),
                new Student("Heba", 20, FEMALE, true, asList(ENGLISH, CHEMISTRY)),
                new Student("Ruba", 30, FEMALE, false, asList(JAVA, MATH, ENGLISH, CHEMISTRY)),
                new Student("Esa", 45, MALE, true, asList(JAVA, MATH, ENGLISH, CHEMISTRY))

        };

        MyHashSet<Student> studHashSet = new MyHashSet<>();
        Stream.of(students)
                .forEach(e->{
                    traceAddStudent(studHashSet,e);
                });


        System.out.println("studHashSet.contains(10) = " + studHashSet.contains(10));
        System.out.println("studHashSet.contains(                new Student(\"Esa\", 45, MALE, true, asList(JAVA, MATH, ENGLISH, CHEMISTRY))) = " + studHashSet.contains(new Student("Esa", 45, MALE, true, asList(JAVA, MATH, ENGLISH, CHEMISTRY))));




    }

    private static void traceAdd(MyHashSet<String> hashSet,String str) {
        int hashCodeForStr = str==null?0:str.hashCode();
        System.out.format("hashCodeFor %s  = %d \n" ,str, hashCodeForStr);
        hashSet.add(str);
        hashSet.debug();
    }

    private static void traceAddStudent(MyHashSet<Student> hashSet,Student st) {
        int hashCodeForStr = st==null?0:st.hashCode();
        System.out.format("hashCodeFor %s  = %d \n" ,st, hashCodeForStr);
        hashSet.add(st);
        hashSet.debug();
    }
}
