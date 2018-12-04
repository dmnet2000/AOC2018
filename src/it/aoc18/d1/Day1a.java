package it.aoc18.d1;

import java.io.*;

public class Day1a {


    public static void main(String[] Args) throws Exception {
        File f = new File("day1aInput.txt");

        BufferedReader br = new BufferedReader(new FileReader(f));

        String line = "";

        int frequenza = 0;
        while ((line = br.readLine()) != null) {
            //determino l'operatore
            char op = line.charAt(0);
            int val = Integer.parseInt(line.substring(1, line.length()));
            //System.out.println(val + " " + op);
            if (op == '+') {
                frequenza += val;
            } else {
                frequenza -= val;
            }
            System.out.println(frequenza);
        }
    }

}