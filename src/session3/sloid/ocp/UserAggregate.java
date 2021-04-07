package session3.sloid.ocp;

public class UserAggregate {

    private User user=new User("ID10","Mohammad",40);

//    private final InMemoryUserRepository repo = new InMemoryUserRepository();
    private final UserRepository repo;

    public UserAggregate(UserRepository repo) {
        this.repo = repo;
    }

    public void save(){
        repo.save(user);
    }

    public void printUser(){

    }


    public static void main(String[] args) {
        UserAggregate us1=new UserAggregate(new InMemoryUserRepository());
        UserAggregate us2=new UserAggregate(new DBUserRepository());
        UserAggregate us3=new UserAggregate(new UserRepository() {
            @Override
            public void save(User user) {

            }
        });

        us1.save();
        us2.save();
    }
}
