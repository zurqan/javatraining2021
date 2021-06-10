package session8ds;

import java.util.Iterator;
import java.util.Optional;

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

    public Optional<E> first(){

        return first == null
                ? Optional.empty()
                : Optional.of(first.data);
    }

    public Optional<E> last(){

        return last ==null
                ?Optional.empty()
                :Optional.of(last.data);
    }

    public void push(E e){
        addFirst(e);
    }

    public void add(E e){
        addLast(e);
    }

    public Optional<E> get(int index){
        if(index>=size|| index<0){
            return Optional.empty();
        }

        Node node = node(index);
        return Optional.of(node.data);
    }

    private Node node(int index) {
        Node nav= first;
        for (int i = 0; i < index; i++) {
            nav=nav.next;
        }
        return nav;
    }

    public void addFirst(E e) {
        Node oldFirstNode = first;
        Node addedNode = new Node(null,oldFirstNode,e);

        first=addedNode;

        if(oldFirstNode == null){
            last=addedNode;
        }else{
            oldFirstNode.previous=addedNode;
        }
        size++;
    }

    public void addLast(E e){
        Node oldLast = last;
        Node newLastNode = new Node(oldLast, null, e);
        last=newLastNode;
        if(oldLast==null){
            first=newLastNode;
        }else{
            oldLast.next=newLastNode;
        }

        size++;
    }

    public void addAll(MyLinkedList<? extends E> another){
        Iterator<? extends E> iterate = another.iterate();
        while (iterate.hasNext()){
            E next = iterate.next();
            add(next);
        }
    }

    public int size(){
        return size;
    }

    public Iterator<E> iterate(){
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
            return currentNode!=null;
        }

        @Override
        public E next() {
            lastAccessedNode= currentNode;
            currentNode=currentNode.next;
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
        another.push(3);

        numbers.addAll(another);

        printList(numbers);

        System.out.println("numbers.get(0) = " + numbers.get(0));
        System.out.println("numbers.get(100) = " + numbers.get(100));
        System.out.println("numbers.get(5) = " + numbers.get(5));
        System.out.println("numbers.get(-1) = " + numbers.get(-1));


    }

    private static void printList(MyLinkedList<Integer> numbers) {
        Iterator<Integer> iterate = numbers.iterate();
        while (iterate.hasNext()){
            Integer next = iterate.next();
            System.out.println("next = " + next);
        }
    }
}
