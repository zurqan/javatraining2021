package session3.sloid.ocp;

public class DBUserRepository  implements UserRepository{
    @Override
    public void save(User user) {
        System.out.println("I will save it in DB");
    }
}
