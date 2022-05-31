package com.lab3;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MDT {
    private static int locCounter = 1;

    private static LinkedHashMap<String, ArrayList<String>> mdt = new LinkedHashMap<String, ArrayList<String>>();

    public static LinkedHashMap<String, ArrayList<String>> getMdt() {
        return mdt;
    }

    public static int getlocCounter() {
        return locCounter;
    }

    public static void insert(String value) {
        ArrayList<String> temp = new ArrayList<String>();
        temp.add(String.valueOf(locCounter));
        temp.add(value);
        mdt.put(String.valueOf(locCounter), temp);
        locCounter++;
    }

    public static String getinst(int location) {
        return mdt.get(String.valueOf(location)).get(1);
    }

    public static void printMDT() {
        System.out.println("--------------MDT Table--------------");
        System.out.println("MDT Index\tInstruction");
        for (ArrayList<String> url : mdt.values()) {
            for(int i  = 0; i < url.size(); i++) {
                System.out.print(url.get(i) + "\t\t");
            }
            System.out.println();
        }
    }
}
