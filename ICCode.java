package com.lab1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class ICCode {
    private ArrayList<ArrayList<String>> icCode = new ArrayList<ArrayList<String>>(); 

    public void addICCode(String arg1, String arg2, String arg3, String arg4) {
        ArrayList<String> temp = new ArrayList<String>();
        temp.add(arg1);
        temp.add(arg2);
        temp.add(arg3);
        temp.add(arg4);
        icCode.add(temp);
    }

    public void printICCode() {
        System.out.println("--------------I/C Code--------------");
        System.out.println("Address\tInstruction Type\tOperand 1\tOperand 2");
        for(int i  = 0; i < icCode.size(); i++) {
            for(int j = 0; j < icCode.get(i).size(); j++) {
                System.out.print(icCode.get(i).get(j) + "\t\t");
            }
            System.out.println();
        }
    }

    public void savetoFile() {
        File fout = new File("ICCode.txt");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(fout);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            for(int i  = 0; i < icCode.size(); i++) {
                String temp = "";
                for(int j = 0; j < icCode.get(i).size(); j++) {
                    temp += icCode.get(i).get(j) + "\t\t";
                }
                bw.write(temp);
                bw.newLine();
            }        
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
