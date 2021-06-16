package session8ds;

import session5.step1.CBiFunction;
import session6.Application.Tuple;
import session6.RecApplication.TailCall;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

import static session6.RecApplication.TailCall.result;
import static session6.RecApplication.TailCall.suspend;

public class MyArrayList<E> {

    private Object[] data;
    private int size;

    public static final int DEFAULT_SIZE =10;

    public MyArrayList(){
      this(new Object[DEFAULT_SIZE],0);
    }

    public MyArrayList(int initialSize){
        this(new Object[initialSize],0);
    }

    public MyArrayList(Object[] data,int size){
        this.data=data;
        this.size=size;
    }

    public static <E> MyArrayList<E> of(E... data){
        return new MyArrayList<>(data, data.length);
    }

    public Optional<E> get(int index){

        return index<0 || index>=size
                ?Optional.empty()
                :Optional.of((E)data[index]);
    }

    public Optional<E> getLast(){
        return get(size-1);
    }
    public Optional<E> getFirst(){
        return get(0);
    }

    public MyArrayList<E> set(int index, E element){

        expandIfNeeded(index);
        data[index]=element;

        size=size>index+1?size:index+1;

        return this;
    }
    public MyArrayList<E> addFirst(E element){
        expandIfNeeded(size);
        System.arraycopy(data,0,data,1,size);
        data[0]=element;
        size++;
        return this;
    }

    public MyArrayList<E> addLast(E element){
        set(size,element);
        return this;
    }

    public <U> U reduceL(U seed, CBiFunction<U,E,U> accFunc){


        return reduceL(seed,accFunc,0).eval();
    }

    public Optional<E> removeByIndex(int index){
        if(index<0 || index>=size)return Optional.empty();

        E removedElement = (E)data[index];

        System.arraycopy(data,index+1,data,index,size-index-1);
        data[--size]=null;
        return Optional.of(removedElement);
    }

    private <U> TailCall<U> reduceL(U acc, CBiFunction<U, E, U> accFunc, int index) {
        return index>=size
                ? result(acc)
                :suspend(()->reduceL(accFunc.apply(acc).apply((E)data[index]),accFunc,index+1));
    }

    public <U> U reduceR(U seed,CBiFunction<E,U,U> accFunc){

        return reduceR(seed,accFunc,size-1);
    }

    private <U> U reduceR(U acc, CBiFunction<E, U, U> accFunc, int index) {
        return index<0
                ?acc
                :reduceR(accFunc.apply((E)data[index]).apply(acc),accFunc,index-1);

    }

    public Iterator<E> iterate(){
        return new MyArrayListIterator(0);
    }

    public <U> MyArrayList<U> scanL(U seed,CBiFunction<U,E,U> accFunction){
        return reduceL(new MyArrayList<U>().addFirst(seed),acc->e->acc.addLast(accFunction.apply((U)acc.getLast().get()).apply(e)));
    }

    public <U> MyArrayList<U> scanR(U seed,CBiFunction<E,U,U> accFunction){

        return reduceR(new MyArrayList<U>().addLast(seed),e->acc->acc.addFirst(accFunction.apply(e).apply((U)acc.get(0).get())));
    }

    public boolean contains(Object targetObject){
        Iterator<E> iterate = iterate();
        while (iterate.hasNext()) {
            E element = iterate.next();
            if(element.equals(targetObject)){
                return true;
            }

        }
        return false;
    }

    public MyArrayList<E> filter(Predicate<? super E> filter){
        return reduceL(new MyArrayList<>(), acc -> e -> filter.test(e) ? acc.addLast(e) : acc);
    }

    public MyArrayList<E> addAll(MyArrayList<? extends E> another){
        // performance issue .. expantion size+another.size if current array less than  total size
        expandIfNeeded(this.size+another.size);
        Iterator<? extends E> iterate = another.iterate();
        while (iterate.hasNext()) {
            E next = iterate.next();
            addLast(next);
        }
        return this;
    }

    public <U> MyArrayList<U> map(Function<? super E,?extends U> mapFunction){

        return reduceL(new MyArrayList<>(),acc->e->acc.addLast(mapFunction.apply(e)));
    }

    public <U> MyArrayList<U> flatMap(Function<? super E,MyArrayList<? extends U>> function){
        return reduceL(new MyArrayList<>(),acc->e->acc.addAll(function.apply(e)));
    }

    public <K> Map<K,MyArrayList<E>> groupBy(Function<E,K> keyMapper){

        return reduceR(
                new HashMap<>(),
                e->acc->{
                    acc.compute(keyMapper.apply(e),(k,v)->v==null?new MyArrayList<E>().addFirst(e):v.addFirst(e));
                    return acc;
                }

        );
    }

    public <U> MyArrayList<Tuple<E,U>> zip(MyArrayList<U> another){
        return null;
    }

    public Optional<E> min(Comparator<E> comparator){


        return isEmpty()
                ?Optional.empty()
                :Optional.of(reduceR(get(size-1).get(),e->acc->comparator.compare(e,acc)>0?acc:e));
    }

    private boolean isEmpty() {
        return size==0;
    }

    public Optional<E> max(Comparator<E> comparator){


        return isEmpty()
                ?Optional.empty()
                :Optional.of(reduceR(get(size-1).get(),e->acc->comparator.compare(e,acc)<0?acc:e));
    }

    private class MyArrayListIterator implements Iterator<E>{

        private int index = 0;

        public MyArrayListIterator(int index) {
            this.index = index;
        }

        @Override
        public boolean hasNext() {
            return index<size;
        }

        @Override
        public E next() {
            if(index>=size || index<0){
                throw new ArrayIndexOutOfBoundsException();
            }
            return (E)data[index++];
        }
    }

    @Override
    public String toString() {
        //[e1,e2,e3]
        return reduceL(
                new StringJoiner(",", "[", "]"),
                acc->e -> acc.add(e==null?"Null":e.toString()))
                .toString();
    }

    private void expandIfNeeded(int index) {
        if(index<data.length)return;

        int newArraySize = data.length*2;
        newArraySize=newArraySize<index?index+1:newArraySize;

        System.out.println("Array is expanded");
        data= Arrays.copyOf(data,newArraySize);
    }

    public static void main(String[] args) {

        MyArrayList<Integer> numbers = MyArrayList.of(10, 20, 30);

        System.out.println("numbers.get(1) = " + numbers.get(1));
        System.out.println("numbers.get(-1) = " + numbers.get(-1));
        System.out.println("numbers.get(3) = " + numbers.get(3));

        System.out.println("numbers.set(0,11) = " + numbers.set(0, 11));
        System.out.println("numbers.size = " + numbers.data.length);
        System.out.println("numbers.set(3,33) = " + numbers.set(3, 33));
        System.out.println("numbers.size = " + numbers.data.length);
        System.out.println("numbers.size = " + numbers.size);
        System.out.println("numbers.set(10,100) = " + numbers.set(10, 100));
        System.out.println("numbers.size = " + numbers.data.length);

        MyArrayList<Integer> tenNumbers = MyArrayList.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println("tenNumbers.reduceL(0,acc->e->acc+e) = " + tenNumbers.reduceL(0, acc -> e -> acc + e));

        System.out.println("tenNumbers.reduceR(0,e->acc->e+acc) = " + tenNumbers.reduceR(0, e -> acc -> e + acc));

        System.out.println("tenNumbers.scanL(0, acc -> e -> acc + e) = " + tenNumbers.scanL(0, acc -> e -> acc + e));

        System.out.println("tenNumbers.scanR(0,e->acc->e+acc) = " + tenNumbers.scanR(0, e -> acc -> e + acc));

        Iterator<Integer> iterate = tenNumbers.iterate();
        while (iterate.hasNext()) {
            Integer next = iterate.next();
            System.out.println("next = " + next);

        }
        System.out.println("tenNumbers.removeByIndex(5) = " + tenNumbers.removeByIndex(5));
        System.out.println("tenNumbers = " + tenNumbers);


        System.out.println("tenNumbers.contains(10) = " + tenNumbers.contains(10));
        System.out.println("tenNumbers.contains(11) = " + tenNumbers.contains(11));


        System.out.println("tenNumbers.filter(e->e%2==0) = " + tenNumbers.filter(e -> e % 2 == 0));


        MyArrayList<Integer> myArrayList = tenNumbers
                .flatMap(e -> MyArrayList.of(10 * e - 1, 10 * e, 10 * e + 1));
        System.out.println("myArrayList = " + myArrayList);

        MyArrayList<String> mapped = tenNumbers.map(n -> "The number is: " + n);
        System.out.println("mapped = " + mapped);

        Map<Boolean, MyArrayList<Integer>> booleanMyArrayListMap = tenNumbers.groupBy(e -> e % 2 == 0);

        System.out.println("booleanMyArrayListMap = " + booleanMyArrayListMap);

        MyArrayList<Integer> first = MyArrayList.of(1, 2);

        MyArrayList<Integer> second = MyArrayList.of(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        System.out.println("first.addAll(second) = " + first.addAll(second));

        System.out.println("tenNumbers.min(Comparator.comparing(a->a)) = " + tenNumbers.min(Comparator.comparing(a -> a)));

        System.out.println("tenNumbers.max(Comparator.comparing(a -> a)) = " + tenNumbers.max(Comparator.comparing(a -> a)));
//
    }
}
