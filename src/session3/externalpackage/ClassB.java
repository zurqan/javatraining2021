package session3.externalpackage;

import session3.apackage.ClassA;

public class ClassB {

    public void accessPublic(){
        ClassA a = new ClassA();
        a.publicMethod();
    }
    public void accessProtected(){
        ClassA classA = new ClassA();
//        classA.protectedMethod();//can not access protected from external package
    }

    public void accessFriendlyMethod(){
        ClassA classA = new ClassA();
//        classA.friendlyMethod();//can not access protected from external package
    }

    public void accessPrivateMethod(){
        ClassA classA = new ClassA();
//        classA.privateMethod();//can not access private method
    }
}
