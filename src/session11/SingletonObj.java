package session11;

public class SingletonObj {

    private static SingletonObj instance;
//    private static SingletonObj instance=new SingletonObj();

    private SingletonObj() {
        System.out.println("Instance Created");

    }

    private static class SingletonObjFactoryHelper{
        private static final SingletonObj INSTANCE=new SingletonObj();
    }

    public static SingletonObj instance() {
//        if(instance == null){
//            synchronized (SingletonObj.class){
//
//                instance =  new SingletonObj();
//            }
//        }

//        synchronized (SingletonObj.class) {
//            if (instance == null) {
//                instance = new SingletonObj();
//            }
//        }

//        System.out.println("Try to get instance");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return SingletonObjFactoryHelper.INSTANCE;
    }


}
