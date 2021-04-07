package session3.sloid.fineinterfaces;

import session3.sloid.ocp.User;

import java.io.OutputStream;

public interface UserExporter {

    OutputStream exportToExcel(User user);
    OutputStream exportToPdf(User user);


}
