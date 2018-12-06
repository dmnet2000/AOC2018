package it.aoc18.d1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Integer freq = 0;
        Map<Integer, Integer> frequenze = new HashMap<>();
        boolean t = false;
        while (!t) {
            for (int i = 0; i < listaCambi.size(); i++) {
                freq = freq + listaCambi.get(i);
//                System.out.println(freq);
                if (i > 0) {
                    if (frequenze.get(freq) != null) {
                        t = true;
                        break;
                    } else {
                        frequenze.put(freq, freq);
                    }
                } else {
                    frequenze.put(freq, freq);
                }
            }//for
        }
        if (t) {
            // frequenze.keySet().stream().forEach(val -> {
            //     System.out.println(val);
            // });
            System.out.println("freq = " + freq);
        }
    }

}

