package session4.step1;

@FunctionalInterface
public interface IntFunc {

    public int apply(int a);

    default IntFunc andThenTemp(IntFunc f) {

        //apply this function then apply passed function (f)
        return x -> {
            int firstResult = apply(x);
            int finalResult = f.apply(firstResult);
            return finalResult;
        };
    }

    default IntFunc andThen(IntFunc f) {

        return x->f.apply(apply(x));//f compos this function
    }


    default IntFunc compos(IntFunc g) {
        return a -> apply(g.apply(a));//(fog)(x) (this compos g)
    }

    default IntFunc composDetailedStructure(IntFunc g) {

        IntFunc composFunction =
                a -> {
                    int firstResult = g.apply(a);
                    int finalResult = apply(firstResult);
                    return finalResult;
                };

        return composFunction;
    }
}
