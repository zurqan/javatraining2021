package session8ds;

import session5.step1.CBiFunction;
import session6.Application.Tuple;
import session6.RecApplication.TailCall;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static session6.RecApplication.TailCall.result;
import static session6.RecApplication.TailCall.suspend;
import static session8ds.Util.currying;

public class MyLinkedList<E> {

    private int size;
    private Node first;
    private Node last;

    private class Node {
        private Node previous;
        private Node next;
        private E data;

        public Node(Node previous, Node next, E data) {
            this.previous = previous;
            this.next = next;
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    public Optional<E> first() {

        return first == null
                ? Optional.empty()
                : Optional.of(first.data);
    }

    public Optional<E> last() {

        return last == null
                ? Optional.empty()
                : Optional.of(last.data);
    }

    public MyLinkedList<E> push(E e) {
        addFirst(e);

        return this;
    }

    public MyLinkedList<E> add(E e) {
        addLast(e);
        return this;
    }

    public Optional<E> get(int index) {
        if (index >= size || index < 0) {
            return Optional.empty();
        }

        Node node = node(index);
        return Optional.of(node.data);
    }

    private Node node(int index) {
        Node nav = first;
        for (int i = 0; i < index; i++) {
            nav = nav.next;
        }
        return nav;
    }

    public MyLinkedList<E> addFirst(E e) {
        Node oldFirstNode = first;
        Node addedNode = new Node(null, oldFirstNode, e);

        first = addedNode;

        if (oldFirstNode == null) {
            last = addedNode;
        } else {
            oldFirstNode.previous = addedNode;
        }
        size++;
        return this;
    }

    public MyLinkedList<E> addLast(E e) {
        Node oldLast = last;
        Node newLastNode = new Node(oldLast, null, e);
        last = newLastNode;
        if (oldLast == null) {
            first = newLastNode;
        } else {
            oldLast.next = newLastNode;
        }

        size++;
        return this;
    }

    public MyLinkedList<E> addAll(MyLinkedList<? extends E> another) {
        Iterator<? extends E> iterate = another.iterate();
        while (iterate.hasNext()) {
            E next = iterate.next();
            add(next);
        }
        return this;
    }

    public Optional<E> removeFirst(){

        return first==null
                ?Optional.empty()
                :Optional.of(removeNode(first));
    }
    public Optional<E> removeLast(){

        return last==null
                ?Optional.empty()
                :Optional.of(removeNode(last));
    }
    public Optional<E> remove(int index){
        if(index<0 || index>= size)return Optional.empty();

        return Optional.of(removeNode(node(index)));
    }




    private E removeNode(Node node) {

        Node previous = node.previous;
        Node next = node.next;

        if(previous==null){
            first=next;
        }else{
            previous.next=next;
        }

        if(next==null){
            last=previous;
        }else{
            next.previous=previous;
        }

        E data = node.data;

        //gc to collect non-ref items
//        node.data=null;//gc
//        node.previous=null;//gc
//        node.next=null;//gc

        size--;
        return data;
    }

    public <U> U reduceL(U seed, BiFunction<? super U, ? super E, ? extends U> accFunction) {
//        return reduceL(seed,acc->e->accFunction.apply(acc,e));
        return reduceL(seed, currying(accFunction));
    }

    //a->b->a
    public <U> U reduceL(U seed, CBiFunction<? super U, ? super E, ? extends U> accFunction) {

        U result = seed;

        Iterator<E> iterate = iterate();
        while (iterate.hasNext()) {
            E next = iterate.next();
            result = accFunction.apply(result).apply(next);
        }
        return result;
    }

    public <U> U reduceR(U seed, BiFunction<? super E, ? super U, ? extends U> accFunction) {

        return reduceR(seed, currying(accFunction));

    }

    private <U> U reduceR(U seed, CBiFunction<? super E, ? super U, ? extends U> accFunction) {
//        return reduceR(seed,accFunction,last);
        return reduceR(seed, accFunction, last).eval();
    }

    public <U> U reduceR(U seed, BiFunction<? super E, ? super U, ? extends U> accFunction, BiFunction<E, U, Boolean> stopCondition) {

        return reduceR(seed, currying(accFunction), currying(stopCondition));

    }

    //    private <U> U reduceR(U acc, CBiFunction<? super E,? super U,? extends U> accFunction, Node node) {
//
//        return node == null
//                ? acc
//                : reduceR(accFunction.apply(node.data).apply(acc), accFunction, node.previous);
//    }

    private <U> TailCall<U> reduceR(U acc, CBiFunction<? super E, ? super U, ? extends U> accFunction, Node node) {

        return node == null
                ? result(acc)
                : suspend(() -> reduceR(accFunction.apply(node.data).apply(acc), accFunction, node.previous));
    }

    private <U, V> U reduceR(U seed, CBiFunction<? super E, ? super U, ? extends U> accFunction, CBiFunction<E, U, Boolean> stopCondition) {

        return reduceR(seed, accFunction, last, stopCondition).eval();

    }

    private <U> TailCall<U> reduceR(U acc, CBiFunction<? super E, ? super U, ? extends U> accFunction, Node node, CBiFunction<E, U, Boolean> stopCondition) {

        U newAccValue = node == null?null:accFunction.apply(node.data).apply(acc);

        return node == null
                ? result(acc)
                : stopCondition.apply(node.data).apply(newAccValue)
                ? result(newAccValue)
                : suspend(() -> reduceR(newAccValue, accFunction, node.previous));
    }

    public MyLinkedList<E> filter(Predicate<? super E> filter) {
        return reduceL(new MyLinkedList<>(), acc -> e -> filter.test(e) ? acc.addLast(e) : acc);
    }

    public boolean allMatch(Predicate<? super E> condition) {

//        if(isEmpty()){
//            return false;
//        }
//        Iterator<E> iterate = iterate();
//        while (iterate.hasNext()) {
//            E next = iterate.next();
//            boolean test = condition.test(next);
//            if(!test)return false;
//        }
//        return true;

//        return isEmpty()
//                ?false
//                :reduceR(true,e->acc->acc&&condition.test(e));//iterate over all element inside the list
        return isEmpty()
                ? false
                : reduceR(true, e -> acc -> acc && condition.test(e), e -> acc -> !acc);
    }

    public boolean noneMatch(Predicate<? super E> condition) {
        return allMatch(condition.negate());
    }

    public boolean anyMatch(Predicate<? super E> condition) {

        return reduceR(false, e -> acc -> condition.test(e), e -> acc -> acc);
    }

    public Optional<E> min(Comparator<E> comparator){


        return isEmpty()
                ?Optional.empty()
                :Optional.of(reduceR(last.data,e->acc->comparator.compare(e,acc)>0?acc:e));
    }

    public Optional<E> max(Comparator<E> comparator){


        return isEmpty()
                ?Optional.empty()
                :Optional.of(reduceR(last.data,e->acc->comparator.compare(e,acc)<0?acc:e));
    }

    public <U> MyLinkedList<Tuple<E,U>> zip(MyLinkedList<U> another){

        return null;
    }

    public <U> Tuple<MyLinkedList<E>,MyLinkedList<U>> unZip(MyLinkedList<Tuple<E,U>> zipped){

        return null;
    }



    private boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }


    @Override
    public String toString() {
        //[e1,e2,e3]
        return reduceL(
                new StringJoiner(",", "[", "]"),
                (acc, e) -> acc.add(e.toString()))
                .toString();
    }

    public MyLinkedList<E> reversed() {
        return reduceR(new MyLinkedList<>(), (e -> acc -> acc.addLast(e)));
    }

    public <U> MyLinkedList<U> map(Function<? super E, ? extends U> mapFunction) {

        return reduceR(new MyLinkedList<>(), e -> acc -> acc.push(mapFunction.apply(e)));
    }

    public <U> MyLinkedList<U> flatMap(Function<? super E, MyLinkedList<? extends U>> flatMap) {
        return reduceL(new MyLinkedList<>(), acc -> e -> acc.addAll(flatMap.apply(e)));
    }

    public Iterator<E> iterate() {
        return new MyLinkedListIterator(first);
    }

    private class MyLinkedListIterator implements Iterator<E> {

        private Node currentNode;
        private Node lastAccessedNode;

        public MyLinkedListIterator(Node currentNode) {
            this.currentNode = currentNode;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public E next() {
            lastAccessedNode = currentNode;
            currentNode = currentNode.next;
            return lastAccessedNode.data;
        }
    }


    public static void main(String[] args) {
        MyLinkedList<Integer> numbers = new MyLinkedList<>();

        numbers.push(10);
        numbers.push(20);
        numbers.push(30);

        printList(numbers);

        System.out.println("numbers.size() = " + numbers.size());

        System.out.println("numbers.first() = " + numbers.first());
        System.out.println("numbers.last() = " + numbers.last());

        MyLinkedList<Integer> another = new MyLinkedList<>();
        another.push(1);
        another.push(2);
        another.push(3);//3  2  1

        numbers.addAll(another);

        printList(numbers);

        System.out.println("numbers.get(0) = " + numbers.get(0));
        System.out.println("numbers.get(100) = " + numbers.get(100));
        System.out.println("numbers.get(5) = " + numbers.get(5));
        System.out.println("numbers.get(-1) = " + numbers.get(-1));


        Integer sum = another.reduceL(0, acc -> e -> acc + e);
        System.out.println("sum = " + sum);


        String reduce = another.reduceR("", e -> acc -> acc + e);//
        System.out.println("reduce = " + reduce);

        System.out.println("another = " + another);
        MyLinkedList<Object> reversed = another.reduceR(new MyLinkedList<>(), (e -> acc -> acc.addLast(e)));
        System.out.println("reversed = " + reversed);


        MyLinkedList<Integer> map = another
                .map(a -> a + 10);
        System.out.println("another = " + another);
        System.out.println("map = " + map);

        MyLinkedList<String> strMap = another.map(e -> "Number is " + e);
        System.out.println("strMap = " + strMap);

        IntStream.rangeClosed(1, 3).map(a -> a + 10).forEach(System.out::println);


        MyLinkedList<Object> flattedList = another.flatMap(e -> {
            MyLinkedList<Object> flatted = new MyLinkedList<>();
            flatted.push(e + 1);
            flatted.push(e);
            flatted.push(e - 1);
            return flatted;
        });


        //321 -> <2 3 4> <1 2 3> <0 1 2>
        System.out.println("flattedList = " + flattedList);

        MyLinkedList<MyLinkedList<Integer>> map1 = another.map(e -> {
            MyLinkedList<Integer> flatted = new MyLinkedList<>();
            flatted.push(e + 1);
            flatted.push(e);
            flatted.push(e - 1);
            return flatted;
        });
        System.out.println("map1 = " + map1);


        another.push(4);
        Predicate<Integer> isEven = e -> e % 2 == 0;
        System.out.println("another.filter(e->e%2==0) = " + another.filter(isEven));
        System.out.println("another. odd= " + another.filter(isEven.negate()));

        System.out.println("another.allMatch(isEven) = " + another.allMatch(isEven));

        System.out.println("another.allMatch(e->e<100) = " + another.allMatch(e -> e < 100));

        System.out.println("another.noneMatch(isEven) = " + another.noneMatch(isEven));
        System.out.println("another.noneMatch(isEven.negate()) = " + another.noneMatch(isEven.negate()));

        System.out.println("another.noneMatch(e->e>100) = " + another.noneMatch(e -> e > 100));


        System.out.println("another.anyMatch(isEven) = " + another.anyMatch(isEven));
        System.out.println("another.anyMatch(isEven.negate()) = " + another.anyMatch(isEven.negate()));
        System.out.println("another.anyMatch(e->e>100) = " + another.anyMatch(e -> e > 100));

        Optional<Integer> min = another.min(Comparator.comparing(x -> x));
        System.out.println("min = " + min);

        System.out.println("another.max(Comparator.comparing(x -> x)) = " + another.max(Comparator.comparing(x -> x)));

        System.out.println("(new MyLinkedList<>()).min() = " + (new MyLinkedList<Integer>()).min(Comparator.comparing(x -> x)));


//        another.removeFirst();
//        System.out.println("another = " + another);

        another.removeLast();
        System.out.println("another = " + another);
    }

    private static void printList(MyLinkedList<Integer> numbers) {
        Iterator<Integer> iterate = numbers.iterate();
        while (iterate.hasNext()) {
            Integer next = iterate.next();
            System.out.println("next = " + next);
        }
    }
}
