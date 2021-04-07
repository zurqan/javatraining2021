package session3.externalpackage;

import session3.apackage.ClassA;

public class ClassExtendA  extends ClassA {

    public void accessPublic(){
        super.publicMethod();
    }

    public void accessProtected(){
        super.protectedMethod();
    }

    public void accesssFriendly(){
//        super.friendlyMethod();//can not access firndlyMethod
    }

    public void accessPrivateMethod(){
//        super.privateMethod();//can not access private method
    }
}
