package it.aoc18.d2;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.List;

public class Day2a {


    public static void main(String[] Args) throws Exception {
        File f = new File("day2Input.txt");

        BufferedReader br = new BufferedReader(new FileReader(f));

        String line = "";
        List<String> listaRighe = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            //determino l'operatore
            listaRighe.add(line);
        }

        //todo: rimuovere dopo controllo
        /*
        listaRighe = new ArrayList<String>();
        String[] lista = {"abcdef",
                 "bababc",
                "abbcde", "abcccd", "aabcdd", "abcdee", "ababab"
        };
        for (String str : lista
        ) {
            listaRighe.add(str);

        }
*/
        List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();
        listaRighe.forEach(row -> {
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < row.length(); i++) {
                char c = row.charAt(i);
                if (map.get(String.valueOf(c)) == null) {
                    map.put(String.valueOf(c), 1);
                } else {
                    int val = map.get(String.valueOf(c));
                    map.put(String.valueOf(c), val + 1);
                }

            }

            list.add(map);
        });

        final Integer[] count2 = {0};
        final Integer[] count3 = {0};

        for (Map<String, Integer> mappa : list) {
            Set<String> key = mappa.keySet();
            System.out.println("-----------------------");
            final boolean[] b2 = {false};
            final boolean[] b3 = {false};
            key.forEach(k -> {
                if(mappa.get(k)==2 && !b2[0]){
                    count2[0]++;
                    b2[0]=true;
                }
                if(mappa.get(k)==3 && !b3[0]){
                    count3[0]++;
                    b3[0]=true;
                }
                System.out.println(k + "=" + mappa.get(k));
            });
            System.out.println("-----------------------");
        }
        System.out.println(count2[0]);
        System.out.println(count3[0]);
        System.out.println(count2[0]*count3[0]);


    }
}