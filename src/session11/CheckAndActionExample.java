package session11;

public class CheckAndActionExample {

//    private  boolean flag;
    private volatile boolean flag;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        //do operation
        return flag;
    }

//    public synchronized boolean isFlag() {
//        return flag;
//    }

    public static void main(String[] args) throws InterruptedException {
        CheckAndActionExample obj = new CheckAndActionExample();
        new Thread(()->{

            System.out.println("Wait for flag to be true");
            while (!obj.isFlag()){
                ;//
            }

            System.out.println("obj.flag = " + obj.flag);
            //do action

        }).start();

        System.out.println("Wait for 4 seconds");
        Thread.sleep(4000);
        obj.setFlag(true);



    }
}
