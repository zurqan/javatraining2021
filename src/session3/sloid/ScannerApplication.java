package session3.sloid;

import java.io.*;
import java.util.Scanner;

public class ScannerApplication {

    public static void main(String[] args) {

        Scanner sc= new Scanner(System.in);

        int value = sc.nextInt();

        System.out.println(value);
    }


//    public void copy(FileInputStream f, FileOutputStream o) throws IOException {
//        byte[] b= new byte[100];
//
//        while(f.read(b)>0){
//            o.write(b);
//        };
//    }
    public void copy(InputStream f, OutputStream o) throws IOException {
        byte[] b= new byte[100];

        while(f.read(b)>0){
            o.write(b);
        };
    }
}
