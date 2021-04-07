package session3.sloid.fineinterfaces;

import session3.sloid.ocp.User;

import java.io.OutputStream;

public interface UserOperation {

    public OutputStream exportToExcel(User user);

    public String jsonFormat(User user);

    public void print(User user);

    public void save(User user);
}
