package session3.sloid.ocp;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserRepository implements UserRepository{

    private List<User> users= new ArrayList<>();

    @Override
    public void save(User user){
        //In Memory DB
        System.out.println("Saving User");
        users.add(user);
    }
}
