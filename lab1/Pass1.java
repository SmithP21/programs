package com.lab1;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Pass1 {
    private static int locCounter = 0;

    public static void start(File file) {
        try {
            Scanner sc = new Scanner(file);
            Table T = new Table();
            ICCode icCode = new ICCode();
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                s = s.replace(",", "");
                StringTokenizer s2 = new StringTokenizer(s);
                while (s2.hasMoreTokens()) {
                    String token = s2.nextToken();
                    if (T.getOptab().containsKey(token)) {
                        if (token.equals("START") || token.equals("ORIGIN")) {
                            if (s2.hasMoreTokens()) { // START xx || ORIGIN xx
                                String t = s2.nextToken();
                                try {
                                    locCounter = Integer.parseInt(t); // Ex. START 400 || ORIGIN 400
                                    icCode.addICCode(
                                            "-", "(" + T.getOptab().get(token).get(0) + ","
                                                    + T.getOptab().get(token).get(1) + ")",
                                            "-", "(C," + locCounter + ")");
                                } catch (Exception e) {
                                    if (Symtab.getSymtab().containsKey(t)) { // Not implemented correctly chech for
                                                                             // below mentioned instruction
                                        if (t.contains("+")) { // Ex. ORIGIN LOOP+2
                                            String part[] = t.split("+");
                                            locCounter = Integer.parseInt(Symtab.getSymtab().get(part[0]).get(2))
                                                    + Integer.parseInt(part[1]);
                                        } else if (t.contains("-")) { // Ex. ORIGIN LOOP-2
                                            String part[] = t.split("-");
                                            locCounter = Integer.parseInt(Symtab.getSymtab().get(part[0]).get(2))
                                                    - Integer.parseInt(part[1]);
                                        } else { // Ex. ORIGIN LOOP
                                            locCounter = Integer.parseInt(Symtab.getSymtab().get(t).get(2));
                                        }
                                        icCode.addICCode("-",
                                                "(" + T.getOptab().get(token).get(0) + ","
                                                        + T.getOptab().get(token).get(1) + ")",
                                                "-", "(S," + Symtab.getSymtab().get(t).get(0) + ")");
                                    }
                                }
                            } else {
                                icCode.addICCode("-", "(" + T.getOptab().get(token).get(0) + ","
                                        + T.getOptab().get(token).get(1) + ")", "-", "-");
                            }
                            locCounter--;
                        } else if (token.equals("END")) {
                            icCode.addICCode(String.valueOf(locCounter),
                                    "(" + T.getOptab().get(token).get(0) + "," + T.getOptab().get(token).get(1) + ")",
                                    "-", "-");
                        } else if (T.getOptab().get(token).get(0).equals("IS")) {
                            String op1 = s2.nextToken();
                            String op2 = s2.nextToken();
                            if (!T.getRegtab().containsKey(op1) && !Symtab.getSymtab().containsKey(op1)) {
                                Symtab.getSymtab().put(op1,
                                        new ArrayList<String>(Arrays.asList(String.valueOf(Symtab.getSymtabptr()), op1,
                                                String.valueOf(locCounter), "1")));
                                Symtab.incSymtabptr();
                            }
                            if (!T.getRegtab().containsKey(op2) && !Symtab.getSymtab().containsKey(op2)) {
                                Symtab.getSymtab().put(op2,
                                        new ArrayList<String>(Arrays.asList(String.valueOf(Symtab.getSymtabptr()), op2,
                                                String.valueOf(locCounter), "1")));
                                Symtab.incSymtabptr();
                            }
                            if (T.getRegtab().containsKey(op1))
                                op1 = "(" + T.getRegtab().get(op1) + ")";
                            else
                                op1 = "(S," + Symtab.getSymtab().get(op1).get(0) + ")";
                            if (T.getRegtab().containsKey(op2))
                                op2 = "(" + T.getRegtab().get(op2) + ")";
                            else
                                op2 = "(S," + Symtab.getSymtab().get(op2).get(0) + ")";
                            icCode.addICCode(String.valueOf(locCounter), "(IS," + T.getOptab().get(token).get(1) + ")",
                                    op1, op2);
                        }
                    } else if (!T.getOptab().containsKey(token) && !T.getRegtab().containsKey(token)) {
                        if (!Symtab.getSymtab().containsKey(token)) {
                            Symtab.getSymtab().put(token,
                                    new ArrayList<String>(Arrays.asList(String.valueOf(Symtab.getSymtabptr()), token,
                                            String.valueOf(locCounter), "1")));
                            Symtab.incSymtabptr();
                        }
                        String inst = s2.nextToken();
                        if (inst.equals("DS") || inst.equals("DC")) {
                            ArrayList<String> temp1 = Symtab.getSymtab().get(token);
                            String location = s2.nextToken();
                            temp1.set(2, String.valueOf(locCounter));
                            icCode.addICCode(String.valueOf(locCounter),
                                    "(" + T.getOptab().get(inst).get(0) + "," + T.getOptab().get(inst).get(1) + ")",
                                    "-", "(C," + location + ")");
                            if (inst.equals("DC"))
                                location = "1";
                            else {
                                locCounter += Integer.parseInt(location) - 1;
                            }
                            temp1.set(3, location);
                            Symtab.getSymtab().put(token, temp1);
                        } else if (inst.equals("ADD") || inst.equals("SUB") || inst.equals("MULT")
                                || inst.equals("DIV")) {
                            String op1 = s2.nextToken();
                            String op2 = s2.nextToken();
                            if (!T.getRegtab().containsKey(op1) && !Symtab.getSymtab().containsKey(op1)) {
                                Symtab.getSymtab().put(op1,
                                        new ArrayList<String>(Arrays.asList(String.valueOf(Symtab.getSymtabptr()), op1,
                                                String.valueOf(locCounter), "1")));
                                Symtab.incSymtabptr();
                            }
                            if (!T.getRegtab().containsKey(op2) && !Symtab.getSymtab().containsKey(op2)) {
                                Symtab.getSymtab().put(op2,
                                        new ArrayList<String>(Arrays.asList(String.valueOf(Symtab.getSymtabptr()), op2,
                                                String.valueOf(locCounter), "1")));
                                Symtab.incSymtabptr();
                            }
                            if (T.getRegtab().containsKey(op1))
                                op1 = "(" + T.getRegtab().get(op1) + ")";
                            else
                                op1 = "(S," + Symtab.getSymtab().get(op1).get(0) + ")";
                            if (T.getRegtab().containsKey(op2))
                                op2 = "(" + T.getRegtab().get(op2) + ")";
                            else
                                op2 = "(S," + Symtab.getSymtab().get(op2).get(0) + ")";
                            icCode.addICCode(String.valueOf(locCounter),
                                    "(" + T.getOptab().get(inst).get(0) + "," + T.getOptab().get(inst).get(1) + ")",
                                    op1, op2);
                        } else if (inst.equals("EQU")) {
                            // Check for int & check if the variable is not present in symtab
                            String op1 = s2.nextToken();
                            if (Symtab.getSymtab().containsKey(op1)) {
                                Symtab.getSymtab().get(token).set(2, Symtab.getSymtab().get(op1).get(2));
                                icCode.addICCode("-",
                                        "(" + T.getOptab().get(inst).get(0) + "," + T.getOptab().get(inst).get(1) + ")",
                                        "-", "(S," + Symtab.getSymtab().get(op1).get(0) + ")");
                                locCounter--;
                            }
                        }
                    }
                }
                locCounter++;
            }
            icCode.printICCode();
            Symtab.printSymtab();
            Symtab.savetoFile();
            icCode.savetoFile();
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
