package session1;

public class ControlFlowExample {

    public static void main(String[] args) {
        tryIf();
        tryWhile();
        tryDoWhile();
        tryFor();
        trySwitchCase();
        tryAddRecursion();
    }

    private static void tryAddRecursion() {
        int addResult = calculateAdd(10,20);
        System.out.println("addResult = " + addResult);
        addResult = calculateAdd(10,200);
        System.out.println("addResult = " + addResult);
//        addResult = calculateAdd(10,30000);
//        System.out.println("addResult = " + addResult);

    }

    private static int calculateAdd(int x, int y) {
//        return x+y;
        return y==0?x:calculateAdd(x+1,y-1);
    }

    private static void trySwitchCase() {
        int a =1;

        switch (a){
            case 1:
            case 2:
                System.out.println("It is less than 3");
                break;
            case 10:
                System.out.println("It is ten");
                break;

        }
    }

    private static void tryIf() {

        int a =10;
        if(a%4 ==0){
            System.out.println("Dividable by 4");
        }else{
            System.out.println("Not dividable by 4");
        }

    }

    private static void tryWhile() {
        int a =0;
        while (a<10){
            System.out.println("a: "+a);
            a++;
        }

        while (a>100){
            System.out.println("a = " + a);
        }
    }

    private static void tryDoWhile() {
        int a=0;
        do{
            System.out.println("do-a = " + a);
            a++;

        }while (a<10);

        do{
            System.out.println("do-a2 = " + a);
        }while (a>100);
    }

    private static void tryFor() {
        for (int i = 0; i < 10; i++) {

            if(i%2 != 0){
                continue;
            }
            System.out.println("for-i = " + i);

            if(i>4){
                break;
            }
        }
    }
}
