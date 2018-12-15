package it.aoc18.d4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;

public class Day4a {

    public static void main(String[] Args) throws Exception {
        File f = new File("day4Input.txt");

        BufferedReader br = new BufferedReader(new FileReader(f));

        String line = "";
        List<String> listaRighe = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            listaRighe.add(line);
        }

        listaRighe.forEach(ln -> System.out.println(ln));
        //TODO --TEST REMOVE
        String[] test =
                {"[1518-11-01 00:00] Guard #10 begins shift",
                        "[1518-11-01 00:05] falls asleep",
                        "[1518-11-01 00:25] wakes up",
                        "[1518-11-01 00:30] falls asleep",
                        "[1518-11-01 00:55] wakes up",
                        "[1518-11-01 23:58] Guard #99 begins shift",
                        "[1518-11-02 00:40] falls asleep",
                        "[1518-11-02 00:50] wakes up",
                        "[1518-11-03 00:05] Guard #10 begins shift",
                        "[1518-11-03 00:24] falls asleep",
                        "[1518-11-03 00:29] wakes up",
                        "[1518-11-04 00:02] Guard #99 begins shift",
                        "[1518-11-04 00:36] falls asleep",
                        "[1518-11-04 00:46] wakes up",
                        "[1518-11-05 00:03] Guard #99 begins shift",
                        "[1518-11-05 00:45] falls asleep",
                        "[1518-11-05 00:55] wakes up"};
        listaRighe = new ArrayList<>();

        for (int i = 0; i < test.length; i++) {
            listaRighe.add(test[i]);
        }
        System.out.println("-------------------------------------");
        listaRighe.forEach(ln -> System.out.println(ln));
        //end TEST
        List<Operation> operationList = new ArrayList<>();

        listaRighe.forEach(ln -> {
            String dataStr = ln.substring(ln.indexOf("[") + 1, ln.indexOf("]"));
            System.out.println(dataStr);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Operation op = new Operation();
            try {
                op.data = sdf.parse(dataStr);
                System.out.println(op.data);
            } catch (Exception e) {
                System.out.println("ERRORE CONVERSIONE DATA");
            }
            op.operation = ln.substring(ln.indexOf("]") + 2, ln.length());
            op.ora = ln.substring(12, ln.indexOf("]"));
            operationList.add(op);
        });

        operationList.forEach(op -> System.out.println("ora:" + op.data + "| op=" + op.operation));
        operationList.sort((op1, op2) -> op1.data.compareTo(op2.data));
        //operationList.forEach(op -> System.out.println("ora:" + op.data + "| op=" + op.operation));
        String id = "";
        for (Operation op : operationList) {
            if (op.operation.contains("#")) {
                String tmp = op.operation.substring(op.operation.indexOf("#") + 1, op.operation.indexOf("begins shift"));

                id = tmp;

            }
            op.id = id;
        }
        operationList.forEach(op -> System.out.println("ora:" + op.data + "|ora=" + op.ora + "|id=" + op.id + "| op=" + op.operation));
        Map<String, Integer> mappaIdSleep = new HashMap<>();
        Map<String, Integer> mappaIdNotSleep = new HashMap<>();
        id = "";
        int oraStart = 0;
        int minStart = 0;
        Stato st = Stato.NOP;
        for (Operation op : operationList) {
            if (op.operation.contains("begins shift")) {
                id = op.id;
                if (mappaIdSleep.get(id) == null) {
                    mappaIdSleep.put(id, 0);

                }
                st = Stato.START;
            }
            if (op.operation.contains("falls asleep") && (st == Stato.START || st == Stato.END_SLEEP)) {
                oraStart = Integer.parseInt(op.ora.substring(0, op.ora.indexOf(":")));
                minStart = Integer.parseInt(op.ora.substring(op.ora.indexOf(":") + 1, op.ora.length()));
                st = Stato.START_SLEEP;

            }
            if (op.operation.contains("wakes up") && st == Stato.START_SLEEP) {
                int oraEnd = Integer.parseInt(op.ora.substring(0, op.ora.indexOf(":")));
                int minEnd = Integer.parseInt(op.ora.substring(op.ora.indexOf(":") + 1, op.ora.length()));
                int value = mappaIdSleep.get(id);

                if (oraEnd == oraStart) {
                    value = value + minEnd - minStart;
                    String[] minuti = new String[60];
                    for (int i = minStart; i <= minEnd; i++) {
                        minuti[i] = "X";
                    }
                    op.listaMinuti.add(minuti);
                } else {
                    if (oraEnd - oraStart > 1) {
                        value = value + (((oraEnd - oraStart) - 1) * 60) + (60 - minStart) + minEnd;
                        for (int i = 0; i < oraEnd - oraStart; i++) {
                            String[] ora = new String[60];
                            for (int j = 0; j <= 59; i++) {
                                ora[j] = "X";
                            }
                            op.listaMinuti.add(ora);
                        }
                        String[] minuti = new String[60];
                        for (int i = minStart; i <= minEnd; i++) {
                            minuti[i] = "X";
                        }
                        op.listaMinuti.add(minuti);
                    } else {
                        value = value + (60 - minStart) + minEnd;
                        String[] minuti = new String[60];
                        for (int i = minStart; i <= minEnd; i++) {
                            minuti[i] = "X";
                        }
                        String[] ora = new String[60];
                        for (int i = 0; i <= 59; i++) {
                            ora[i] = "X";
                        }
                        op.listaMinuti.add(minuti);
                        op.listaMinuti.add(ora);
                    }
                }

                mappaIdSleep.put(id, value);
                st = Stato.END_SLEEP;
            }
        }
        String[] idMaxSLeep = {""};
        int value = 0;
        Set<String> key = mappaIdSleep.keySet();
        for (String k : key) {
            System.out.println("id=" + k + "value=" + mappaIdSleep.get(k));
            if (mappaIdSleep.get(k) > value) {
                value = mappaIdSleep.get(k);
                idMaxSLeep[0] = k;
            }

        }

        int[] minMaxSlip = new int[60];
        for (int i = 0; i < 60; i++) {
            minMaxSlip[i] = 0;
        }

        for (Operation op : operationList) {
            if (op.id.equals(idMaxSLeep[0])) {
                for (String[] minuti : op.listaMinuti) {
                    for (int i = 0; i < 60; i++) {
                        if (minuti[i].equals("X")) {
                            minMaxSlip[i] = minMaxSlip[i] + 1;
                        }
                    }
                }
            }
        }

        int index = 0;
        int val = 0;
        for (int i = 0; i < 60; i++) {
            if (minMaxSlip[i] > val) {
                val = minMaxSlip[i];
                index = i;
            }
        }

        System.out.print("result = " + Integer.parseInt(idMaxSLeep[0]) * index);
    }


    static class Operation {
        public Date data;
        public String operation;
        public String id;
        public String ora;
        public List<String[]> listaMinuti;

        public Operation() {
            listaMinuti = new ArrayList<>();
        }
    }

    private enum Stato {
        START, START_SLEEP, END_SLEEP, NOP;
    }

}
