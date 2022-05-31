package com.lab3;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MNT {
    private static int locCounter = 1;

    private static LinkedHashMap<String, ArrayList<String>> mnt = new LinkedHashMap<String, ArrayList<String>>();

    public static LinkedHashMap<String, ArrayList<String>> getMnt() {
        return mnt;
    }

    public static void insert(int location, String value) {
        ArrayList<String> temp = new ArrayList<String>();
        temp.add(String.valueOf(locCounter));
        temp.add(value);
        temp.add(String.valueOf(location));
        mnt.put(String.valueOf(locCounter), temp);
        locCounter++;
    }

    public static void printMNT() {
        System.out.println("--------------MNT Table--------------");
        System.out.println("MNT Index\tMacro Name\tMDT Indexs");
        for (ArrayList<String> url : mnt.values()) {
            for(int i  = 0; i < url.size(); i++) {
                System.out.print(url.get(i) + "\t\t");
            }
            System.out.println();
        }
    }

    public static int find(String macro) {
        for (ArrayList<String> url : mnt.values()) {
            if(url.get(1).equals(macro)) {
                return Integer.parseInt(url.get(2));
            }
        }
        return -1;
    }
}
