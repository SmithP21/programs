package com.lab3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Pass2 {
    public static void start(File file) {
        try {
            Scanner sc = new Scanner(file);
            File fout = new File("FinalOutputFile.txt");
            FileOutputStream fos;
            fos = new FileOutputStream(fout);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                StringTokenizer st = new StringTokenizer(line);
                String inst = st.nextToken();
                if(MNT.find(inst) != -1) {
                    int mdtindex = MNT.find(inst);
                    String[] instruction = MDT.getinst(mdtindex).split(" ");
                    instruction = Arrays.copyOfRange(instruction, 1, instruction.length);
                    LinkedHashMap<String, String> temp = new LinkedHashMap<String, String>();
                    for (String x : instruction) {
                        String y = st.nextToken();
                        temp.put(x, y);
                        ALA.update(x, y);
                    }
                    while(true) {
                        mdtindex++;
                        line = MDT.getinst(mdtindex);
                        if(line.equals("MEND")) break;
                        for(String x : temp.keySet()) {
                            if(line.contains(x)) {
                                line = line.replace(x, temp.get(x));
                            }
                        }
                        String tempt = "+";
                        tempt += line;
                        bw.write(tempt);
                        bw.newLine();
                    }
                }
                else {
                    bw.write(line);
                    bw.newLine();
                }
            }
            bw.close();
            sc.close();
            System.out.println("ALA Table: ");
            ALA.printALA();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
