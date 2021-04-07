package session3.sloid.fineinterfaces;

import session3.sloid.ocp.User;

import java.io.OutputStream;

public class UserExporterImpl implements  UserExporter{

    @Override
    public OutputStream exportToExcel(User user) {
        //do your impl
        return null;
    }

    @Override
    public OutputStream exportToPdf(User user) {
        //do your impl
        return null;
    }
}

//public class UserExcelExporter implements UserOperation {
//    @Override
//    public OutputStream exportToExcel(User user) {
//        //return excel output stream
//        return null;
//    }
//
//    @Override
//    public String jsonFormat(User user) {
//
//        return "";
//    }
//
//    @Override
//    public void print(User user) {
//        //do nothing
//    }
//
//    @Override
//    public void save(User user) {
//        //do nothing
//    }
//}
