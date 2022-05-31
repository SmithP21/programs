package com.lab1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Symtab {
    private static int symtabptr = 1;
    private static LinkedHashMap<String, ArrayList<String>> symtab = new LinkedHashMap<String, ArrayList<String>>();

    public static LinkedHashMap<String, ArrayList<String>> getSymtab() {
        return symtab;
    }

    public static void incSymtabptr() {
        symtabptr++;
    }

    public static int getSymtabptr() {
        return symtabptr;
    }

    public static void printSymtab() {
        System.out.println("--------------Symbol Table--------------");
        System.out.println("Sym_id\tSym_name\tSym_address\tLength");
        for (ArrayList<String> url : Symtab.getSymtab().values()) {
            for(int i  = 0; i < url.size(); i++) {
                System.out.print(url.get(i) + "\t\t");
            }
            System.out.println();
        }
    }

    public static void savetoFile() {
        File fout = new File("Symtab.txt");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(fout);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            for (ArrayList<String> url : Symtab.getSymtab().values()) {
                String temp = "";
                for(int i  = 0; i < url.size(); i++) {
                    temp += url.get(i) + "\t\t";
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
