package session11;

import java.util.ArrayList;
import java.util.List;

public class PublishThisExample {

    private List<Integer> numbers= null;

    private  ThreadLocal<PublicationObject> loc=ThreadLocal.withInitial(()->new PublicationObject());

    public List<Integer> getNumbers() {
        return new ArrayList<>(numbers);
    }

    public PublishThisExample(EventSource eventSource) {
        eventSource.register(this);

        new Thread(()->{

        }).start();
        //operation need 1 sec
        numbers=new ArrayList<>();


    }
}
