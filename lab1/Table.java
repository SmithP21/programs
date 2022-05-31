package com.lab1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Table {
    private HashMap<String, List<String>> optab;
    private HashMap<String, String> regtab;

    public Table() {
        optab = new HashMap<String, List<String>>();
        regtab = new HashMap<String, String>();
        fillOptab();
        fillRegtab();
    }

    private void fillOptab() {
        optab.put("STOP", new ArrayList<String>(Arrays.asList("IS", "00")));
        optab.put("ADD", new ArrayList<String>(Arrays.asList("IS", "01")));
        optab.put("SUB", new ArrayList<String>(Arrays.asList("IS", "02")));
        optab.put("MULT", new ArrayList<String>(Arrays.asList("IS", "03")));
        optab.put("MOVER", new ArrayList<String>(Arrays.asList("IS", "04")));
        optab.put("MOVEM", new ArrayList<String>(Arrays.asList("IS", "05")));
        optab.put("COMP", new ArrayList<String>(Arrays.asList("IS", "06")));
        optab.put("BC", new ArrayList<String>(Arrays.asList("IS", "07")));
        optab.put("DIV", new ArrayList<String>(Arrays.asList("IS", "08")));
        optab.put("READ", new ArrayList<String>(Arrays.asList("IS", "09")));
        optab.put("PRINT", new ArrayList<String>(Arrays.asList("IS", "10")));
        optab.put("DC", new ArrayList<String>(Arrays.asList("DL", "01")));
        optab.put("DS", new ArrayList<String>(Arrays.asList("DL", "02")));
        optab.put("START", new ArrayList<String>(Arrays.asList("AD", "01")));
        optab.put("END", new ArrayList<String>(Arrays.asList("AD", "02")));
        optab.put("ORIGIN", new ArrayList<String>(Arrays.asList("AD", "03")));
        optab.put("EQU", new ArrayList<String>(Arrays.asList("AD", "04")));
        optab.put("LTORG", new ArrayList<String>(Arrays.asList("AD", "05")));
    }

    private void fillRegtab() {
        regtab.put("AREG", "01");
        regtab.put("BREG", "02");
        regtab.put("CREG", "03");
        regtab.put("DREG", "04");
    }

    public HashMap<String, List<String>> getOptab() {
        return optab;
    }

    public HashMap<String, String> getRegtab() {
        return regtab;
    }
}
