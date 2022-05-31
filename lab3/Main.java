package com.lab3;

import java.io.File;

public class Main {
    public static void main(String ar[]) {
        try {
            File file = new File("D:\\TRI - 9\\SSC\\Lab\\temp\\src\\com\\lab3\\program.asm");
            // File file = new File("src\\com\\lab3\\program.asm");
            Pass1.start(file);
            // File fout = new File("D:\\TRI - 9\\SSC\\Lab\\temp\\OutputFile.txt");
            // Pass2.start(fout);
        }
        catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}