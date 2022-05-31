package com.lab3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Pass1 {
    public static void start(File file) {
        try {
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                StringTokenizer st = new StringTokenizer(line);
                String opcode = st.nextToken();
                if(opcode.equals("MACRO")) {
                    line = sc.nextLine();
                    StringTokenizer s = new StringTokenizer(line);
                    String macro = s.nextToken();
                    ArrayList<String> argList = new ArrayList<String>();
                    while(s.hasMoreTokens()) {
                        argList.add(s.nextToken());
                    }
                    int mdtindex = MDT.getlocCounter();
                    MDT.insert(line);
                    MNT.insert(mdtindex, macro);
                    ALA.insert(argList);
                    while(!line.equals("MEND")) {
                        line = sc.nextLine();
                        MDT.insert(line);
                    }
                    continue;
                }
                else {
                    File fout = new File("OutputFile.txt");
                    FileOutputStream fos;
                    try {
                        fos = new FileOutputStream(fout);
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                        while(sc.hasNextLine()) {
                            bw.write(line);
                            bw.newLine();
                            line = sc.nextLine();
                        }
                        bw.write(line);
                        bw.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            sc.close();
            ALA.printALA();
            MNT.printMNT();
            MDT.printMDT();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
