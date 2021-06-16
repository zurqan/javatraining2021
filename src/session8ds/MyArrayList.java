package session8ds;

import session5.step1.CBiFunction;

import java.util.Arrays;
import java.util.Optional;

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


        return null;
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

    }
}
