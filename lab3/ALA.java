package com.lab3;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ALA {
    private static int locCounter = 1;

    private static LinkedHashMap<String, ArrayList<String>> ala = new LinkedHashMap<String, ArrayList<String>>();

    public static void insert(ArrayList<String> argList) {
        for (String string : argList) {
            ArrayList<String> temp = new ArrayList<String>();
            temp.add("#"+String.valueOf(locCounter));
            temp.add(string);
            ala.put(string, temp);
            locCounter++;
        }
    }

    public static void printALA() {
        System.out.println("--------------ALA Table--------------");
        System.out.println("ALA Index\tFormal Arguments\tArguments");
        for (ArrayList<String> url : ala.values()) {
            for(int i  = 0; i < url.size(); i++) {
                System.out.print(url.get(i) + "\t\t");
            }
            System.out.println();
        }
    }

    public static void update(String from, String to) {
        ArrayList<String> temp = ala.get(from);
        temp.add(to);
        ala.put(from, temp);
    }
}
