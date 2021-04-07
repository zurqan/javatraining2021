package session3.sloid.fineinterfaces;

import session3.sloid.ocp.User;

public interface UserRepository {

    String save(User user);

    User findUser(String id);
}
