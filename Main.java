package com.lab1;
import java.io.File;

public class Main {    
    public static void main(String ar[]) {        
        try {
            File file = new File("D:\\TRI - 9\\Python Elective\\program.asm");
            Pass1.start(file);
            File file2 = new File("D:\\TRI - 9\\SSC\\Lab\\temp\\ICCode.txt");
            Pass2.start(file2);
        }
        catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}