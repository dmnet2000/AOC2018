package it.aoc18.d3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Day3a {
    public static void main(String[] Args) throws Exception {
        File f = new File("day3Input.txt");

        BufferedReader br = new BufferedReader(new FileReader(f));

        String line = "";
        List<String> listaRighe = new ArrayList<>();

        String test[] = {"#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2"};

        while ((line = br.readLine()) != null) {
            listaRighe.add(line);
        }
        //TODO:rimuovere
        /*listaRighe = new ArrayList<>();
        for (int i = 0; i < test.length; i++) {
            listaRighe.add(test[i]);
        }*/
        List<Claim> listaClaim = new ArrayList<>();
        for (String l : listaRighe) {
            Claim claim = new Claim();
            String id = l.substring(l.indexOf("#") + 1, l.indexOf("@") - 1);

            claim.id = String.format("%04d", Integer.parseInt(id));
            claim.sx = Integer.parseInt(l.substring(l.indexOf("@") + 2, l.indexOf(",")));
            claim.top = Integer.parseInt(l.substring(l.indexOf(",") + 1, l.indexOf(":")));
            claim.larghezza = Integer.parseInt(l.substring(l.indexOf(":") + 2, l.indexOf("x")));
            claim.altezza = Integer.parseInt(l.substring(l.indexOf("x") + 1, l.length()));

            System.out.println(claim);
            listaClaim.add(claim);
        }

        int length = 1020;
        //int length = 8;
        String[][] matrix = new String[length][length];


        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                matrix[i][j] = ".";
            }
        }

        int count = 0;

        Map<String, Claim> id = new HashMap<>();
        for (Claim claim : listaClaim) {
            for (int j = claim.top; j < claim.top + claim.altezza; j++) {
                for (int i = claim.sx; i < claim.larghezza + claim.sx; i++) {
                    if (matrix[j][i].equals(".")) {
                        matrix[j][i] = claim.id;
                    } else {

                        id.put(matrix[j][i], new Claim());
                        count++;
                        id.put(claim.id, claim);
                        matrix[j][i] = "X";
                    }
                }
            }

        }

        int count1 = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (matrix[i][j].equals(".")) {
                    System.out.print("  . ");
                } else if (matrix[i][j].equals("X")) {
                    System.out.print("  X ");
                    count1++;
                } else {
                    System.out.print(matrix[i][j]);

                }
                if (j < length - 1) {
                    System.out.print("|");
                }
            }
            System.out.print("\n");
        }
        System.out.println("conflitti = " + count);
        System.out.println("conflitti = " + count1);

        Set<String> keySet = id.keySet();
        //keySet.forEach(key -> System.out.println(key));


        for (Claim claim : listaClaim) {
            boolean trovato = false;
            for (String key : keySet) {
                if (claim.id.equals(key)) {
                    trovato = true;
                    //escludere conflitto
                    //System.out.println("Trovato = " + claim.id);
                    break;
                }
            }
            if (!trovato) {
                System.out.print("ID PULITO=" + claim.id);
            }
        }
    }

    static class Claim {
        public String id;
        public int sx;
        public int top;
        public int larghezza;
        public int altezza;

        public Claim() {
        }


        @Override
        public java.lang.String toString() {
            return "" + id + "->" + sx + "-" + top + "|" + larghezza + "-" + altezza;
        }
    }

}
