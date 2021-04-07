package session3.apackage;

public class ClassB {

    public void accessPublic(){
        ClassA a = new ClassA();
        a.publicMethod();
    }

    public void accessProtected(){
        ClassA classA = new ClassA();
        classA.protectedMethod();
    }

    public void accessFriendlyMethod(){
        ClassA classA = new ClassA();
        classA.friendlyMethod();
    }

    public void accessPrivateMethod(){
        ClassA classA = new ClassA();
//        classA.privateMethod();//can not access private method
    }
}
