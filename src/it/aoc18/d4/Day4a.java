package it.aoc18.d4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
                        { "[1518-11-01 00:00] Guard #10 begins shift",
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
                          "[1518-11-05 00:55] wakes up" };
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

                        operationList.add(op);
                });

        }

        static class Operation {
                public Date data;
                public String operation;
        }
}
