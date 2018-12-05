package it.aoc18.d1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Day1b {


    public static void main(String[] Args) throws Exception {
        File f = new File("day1aInput.txt");

        BufferedReader br = new BufferedReader(new FileReader(f));

        String line = "";

        List<Integer> listaCambi = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            //determino l'operator
            listaCambi.add(Integer.parseInt(line));
        }
        //Integer arr[] = {-6, +3, +8, +5, -6};
        //listaCambi = new ArrayList<Integer>();
        //for(int i =0; i<arr.length;i++){
        //    listaCambi.add(arr[i]);
        //}
        Integer[] freq = {0};
        List<Integer> frequenze = new ArrayList<>();
        boolean t = false;
        while(!t) {
            for (int i = 0; i < listaCambi.size() ; i++) {
                freq[0] = freq[0] + listaCambi.get(i);
                System.out.println(freq[0]);
                if (i > 0) {
                    boolean trovato = false;
                    for (Integer fr : frequenze) {
                        if (fr.equals(freq[0])) {
                            trovato = true;
                            break;
                        }
                    }
                    if (trovato) {
                        t = true;
                        break;
                    }
                    frequenze.add(freq[0]);
                } else {
                    frequenze.add(freq[0]);
                }
            }//for
        }
        if (t) {
            frequenze.forEach(val -> {
                System.out.println(val);
            });
            System.out.println("freq = " + freq[0]);
        }
    }

}

