package session10;


import session5.step1.CBiFunction;
import session6.Application.Tuple;
import session6.RecApplication.TailCall;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static session6.RecApplication.TailCall.result;
import static session6.RecApplication.TailCall.suspend;

public abstract class FList<E> {

    public abstract E head();

    public abstract FList<E> tail();

    public abstract boolean isEmpty();

    public abstract FList<E> set(E head);

    public abstract FList<E> drop(int n);

    public abstract FList<E> dropWhile(Predicate<E> condition);

    public abstract FList<E> reverse();

    public abstract FList<E> init();//[1,2,3,4] -> [1,2,3]

    public abstract int size();

    public abstract <U> U reduceL(U seed, CBiFunction<U, E, U> reduceFunction);

    public abstract <U> U reduceR(U seed, CBiFunction<E, U, U> reduceFunction);

    public abstract <U> FList<U> map(Function<E, U> mapFunc);

    public abstract <U> FList<U> flatMap(Function<E, FList<U>> flatMapFunc);

    public abstract FList<E> filter(Predicate<E> condition);

    private static final Empty EMPTY = new Empty();

    public static <U> FList<U> empty() {
        return EMPTY;
    }

    public FList<E> addFirst(E head) {
        return new FListImpl<>(head, this);
    }

    public static <E> FList<E> concat(FList<E> first, FList<E> second) {
        //[1 2 3 4] , [5 6 7 8] ->
        // 4 , acc= [5 6 7 8], -> [ 4 5 6 7 8]
        //3, acc =[ 4 5 6 7 8] -> [ 3 4 5 6 7 8]
        //2, acc =[ 3 4 5 6 7 8] -> [ 2 3 4 5 6 7 8]
        //1, acc =[ 1 3 4 5 6 7 8] -> [ 1 2 3 4 5 6 7 8]
        return first.reduceR(second, e -> acc -> acc.addFirst(e));

    }

    public static <U, E> U reduceL(U seed, CBiFunction<U, E, U> reduceFunc, FList<E> list) {
        return list.reduceL(seed, reduceFunc);
    }

    public static <E> FList<E> of(E... data) {
        FList<E> acc = empty();
        for (int i = data.length - 1; i >= 0; i--) {
            acc = acc.addFirst(data[i]);
        }

        return acc;
    }

    private static class Empty<E> extends FList<E> {

        @Override
        public E head() {
            throw new IllegalStateException("No Head for empty");
        }

        @Override
        public FList<E> tail() {
            throw new IllegalStateException("No Tail for empty");
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public FList<E> set(E head) {
            throw new IllegalStateException("you can not set Head for empty");
        }

        @Override
        public FList<E> drop(int n) {
            return this;
        }

        @Override
        public FList<E> dropWhile(Predicate<E> condition) {
            return this;
        }

        @Override
        public FList<E> reverse() {
            return this;
        }

        @Override
        public FList<E> init() {
            return this;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public <U> U reduceL(U seed, CBiFunction<U, E, U> reduceFunction) {
            return seed;
        }

        @Override
        public <U> U reduceR(U seed, CBiFunction<E, U, U> reduceFunction) {
            return seed;
        }

        @Override
        public <U> FList<U> map(Function<E, U> mapFunc) {
            return empty();
        }

        @Override
        public <U> FList<U> flatMap(Function<E, FList<U>> flatMapFunc) {
            return empty();
        }

        @Override
        public FList<E> filter(Predicate<E> condition) {
            return this;
        }

        @Override
        public String toString() {
            return "[NIL]";
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Empty;
        }
    }

    private static class FListImpl<E> extends FList<E> {
        private final E head;
        private final FList<E> tail;
        private final int size;

        public FListImpl(E head, FList<E> tail) {
            this.head = head;
            this.tail = tail;
            this.size = tail.size() + 1;
        }

        @Override
        public E head() {
            return head;
        }

        @Override
        public FList<E> tail() {
            return tail;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public FList<E> set(E newHead) {
            return new FListImpl<>(newHead, tail);
        }

        @Override
        public FList<E> drop(int n) {
            return n <= 0
                    ? this
                    : drop(this, n).eval();
        }

        private TailCall<FList<E>> drop(FList<E> efList, int n) {
            return n <= 0 || efList.isEmpty()
                    ? result(efList)
                    : suspend(() -> drop(efList.tail(), n - 1));
        }

        @Override
        public FList<E> dropWhile(Predicate<E> condition) {
            return dropWhile(this, condition).eval();
        }

        private TailCall<FList<E>> dropWhile(FList<E> efList, Predicate<E> condition) {
            return efList.isEmpty() || !condition.test(efList.head())
                    ? result(efList)
                    : suspend(() -> dropWhile(efList.tail(), condition));
        }

        @Override
        public FList<E> reverse() {
            return reverse(empty(), this).eval();
        }

        private TailCall<FList<E>> reverse(FList<E> acc, FList<E> efList) {

            return efList.isEmpty()
                    ? result(acc)
                    : suspend(() -> reverse(new FListImpl<>(efList.head(), acc), efList.tail()));
        }

        @Override
        public FList<E> init() {
            //[1 2 3 4 5 ] -> [1 2 3 4 ]
            //[5 4 3 2 1] -> [ 4 3 2 1]-> [ 1 2 3 4]
            return reverse().tail().reverse();
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public <U> U reduceL(U seed, CBiFunction<U, E, U> reduceFunction) {
            return reduceL(this, seed, reduceFunction).eval();
        }

        private <U> TailCall<U> reduceL(FList<E> efList, U acc, CBiFunction<U, E, U> reduceFunction) {

            return efList.isEmpty()
                    ? result(acc)
                    : suspend(() -> reduceL(efList.tail(), reduceFunction.apply(acc).apply(efList.head()), reduceFunction));
        }

        @Override
        public <U> U reduceR(U seed, CBiFunction<E, U, U> reduceFunction) {
            return reverse().reduceL(seed, e -> acc -> reduceFunction.apply(acc).apply(e));
        }

        @Override
        public <U> FList<U> map(Function<E, U> mapFunc) {
            return reduceR(empty(), e -> acc -> new FListImpl<>(mapFunc.apply(e), acc));
        }

        @Override
        public <U> FList<U> flatMap(Function<E, FList<U>> flatMapFunc) {
            return reduceR(FList.empty(), e -> acc -> concat(flatMapFunc.apply(e), acc));
        }

        @Override
        public FList<E> filter(Predicate<E> condition) {
            return reduceR(FList.empty(), e -> acc -> condition.test(e) ? acc.addFirst(e) : acc);
        }

        @Override
        public String toString() {
            return String.format("[%sNil]", toString(new StringBuilder(), this).eval());
        }

        private TailCall<StringBuilder> toString(StringBuilder stringBuilder, FList<E> efList) {
            return efList.isEmpty()
                    ? result(stringBuilder)
                    : suspend(() -> toString(stringBuilder.append(efList.head()).append(","), efList.tail()));
        }
    }

    public static <E, U> FList<Tuple<E, U>> tupleZip(FList<E> eList, FList<U> uList) {
        return zip(eList, uList, e -> u -> new Tuple<>(e, u));
    }

    public static <E, U, Z> FList<Z> zip(FList<E> eList, FList<U> uList, CBiFunction<E, U, Z> zippingFunction) {
        return zip(empty(), eList, uList, zippingFunction).eval().reverse();
    }

    private static <U, E, Z> TailCall<FList<Z>> zip(FList<Z> acc, FList<E> eList, FList<U> uList, CBiFunction<E, U, Z> zf) {

        return eList.isEmpty() || uList.isEmpty()
                ? result(acc)
                : suspend(
                () ->
                        zip(
                                acc.addFirst(zf.apply(eList.head()).apply(uList.head())),
                                eList.tail(),
                                uList.tail(),
                                zf)
        );
    }

    public static <E, U> Tuple<FList<E>, FList<U>> unZip(FList<Tuple<E, U>> zipped) {
        return zipped.reduceR(new Tuple<>(empty(), empty()), e -> acc -> acc.map(a1 -> a1.addFirst(e._1), a2 -> a2.addFirst(e._2)));
    }

    //[1,2,3] [4,5] *
    //[ 4,5, 8,10, 12,15 ]
    public static <E, U, P> FList<P> product(FList<E> eList, FList<U> ulist, CBiFunction<E, U, P> productFunction) {

        return eList.flatMap(e -> ulist.map(u -> productFunction.apply(e).apply(u)));
    }

    //[0,-1,1,2,3,4] [1,2] ->true
    //[1,2,3,4] [1,3] ->false
    public static <E> boolean hasSubList(FList<E> target, FList<E> sub) {

        return hasSubList_(target, sub).eval();
    }

    private static <E> TailCall<Boolean> hasSubList_(FList<E> target, FList<E> sub) {

        return target.isEmpty()
                ? result(sub.isEmpty())
                : startsWith(target, sub)
                ? result(true)
                : suspend(() -> hasSubList_(target.tail(), sub));
    }

    //[1,2,3,4] [1,2] ->true
    //[1,2,3,4] [1,3] ->false
    //[1,2,3,4] [] ->true
    public static <E> boolean startsWith(FList<E> target, FList<E> sub) {

        return startsWith_(target, sub).eval();
    }

    private static <E> TailCall<Boolean> startsWith_(FList<E> target, FList<E> sub) {
        return
                sub.isEmpty()
                        ? result(true)
                        : target.isEmpty() ?
                        result(false)
                        : !Objects.equals(target.head(), sub.head())
                        ? result(false)
                        : suspend(() -> startsWith_(target.tail(), sub.tail()));
    }

    public static <E, A> FList<E> unReduce(A start,  Function<A, Optional<Tuple<E, A>>> unReduceFunc) {


        return unReduce(empty(), start, unReduceFunc).eval().reverse();
    }

    private static <E, A> TailCall<FList<E>> unReduce(FList<E> target, A lastAcc,  Function<A, Optional<Tuple<E, A>>> unReduceFunc) {

        Optional<Tuple<E, A>> result = unReduceFunc.apply(lastAcc);
        System.out.println("lastAcc = " + lastAcc);
        return
                result
                        .<TailCall<FList<E>>>map(t -> suspend(() -> unReduce(target.addFirst(t._1),  t._2, unReduceFunc)))
                        .orElse(result(target));
    }

    public static FList<Integer> range(int start,int end){
        return unReduce(start,n->n>=end?Optional.empty():Optional.of(new Tuple<>(n,n+1)));
    }

    public static <E> FList<E> fill(int n, Supplier<E> sup){
        return range(0, n).map(a -> sup.get());
    }

}
