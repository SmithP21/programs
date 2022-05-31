package com.lab1;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Pass2 {
    private static ArrayList<ArrayList<String>> machineCode = new ArrayList<ArrayList<String>>();

    public static void start(File file) {
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                StringTokenizer stoken = new StringTokenizer(s, "\t\t");
                if (stoken.hasMoreTokens()) {
                    String word1 = stoken.nextToken();
                    String[] word2 = stoken.nextToken().replace("(", "").replace(")", "").split(",");
                    String word3 = stoken.nextToken().replace("(", "").replace(")", "");
                    String[] word4 = stoken.nextToken().replace("(", "").replace(")", "").split(",");
                    if (word2[0].equals("AD")) {
                        machineCode.add(new ArrayList<String>());
                    } else if (word2[0].equals("DL")) {
                        ArrayList<String> temp = new ArrayList<String>();
                        temp.add(word1);
                        temp.add(word2[1]);
                        temp.add(word3);
                        if (word4[0].equals("C")) {
                            temp.add(word4[1]);
                        } else {
                            for (ArrayList<String> url : Symtab.getSymtab().values()) {
                                if (url.get(0) == word4[1]) {
                                    temp.add(url.get(2));
                                }
                            }
                        }
                        machineCode.add(temp);
                    } else { // IS
                        ArrayList<String> temp = new ArrayList<String>();
                        temp.add(word1);
                        temp.add(word2[1]);
                        temp.add(word3);
                        for (ArrayList<String> url : Symtab.getSymtab().values()) {
                            if (url.get(0).equals(word4[1])) {
                                temp.add(url.get(2));
                            }
                        }
                        machineCode.add(temp);
                    }
                }
            }
            sc.close();
            System.out.println("--------------Machine Code--------------");
            System.out.println("Address\tInstruction Code\tOperand 1\tOperand 2");
            for (int i = 0; i < machineCode.size(); i++) {
                for (int j = 0; j < machineCode.get(i).size(); j++) {
                    System.out.print(machineCode.get(i).get(j) + "\t\t");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void printmachineCode() {
        System.out.println("Machine Code");
        System.out.println("Address\tInstruction Type\tOperand 1\tOperand 2");
        for (int i = 0; i < machineCode.size(); i++) {
            for (int j = 0; j < machineCode.get(i).size(); j++) {
                System.out.print(machineCode.get(i).get(j) + "\t\t");
            }
            System.out.println();
        }
    }
}
