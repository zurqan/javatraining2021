package session4.step1;

public interface StrFunc {

    public String apply(String a);


    default StrFunc compos(StrFunc g){

        return x->apply(g.apply(x));
    }

    default StrFunc andThen(StrFunc f){

        return x->f.apply(apply(x));
    }
}
