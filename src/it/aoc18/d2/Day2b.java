package it.aoc18.d2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Day2b {


    public static void main(String[] Args) throws Exception {
        File f = new File("day2Input.txt");

        BufferedReader br = new BufferedReader(new FileReader(f));

        String line = "";
        List<String> listaRighe = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            //determino l'operatore
            listaRighe.add(line);
        }

        Map<String, Pair> mappa = new HashMap<String,Pair>();
        for(int i = 0; i<listaRighe.size();i++){
            String riferimento = listaRighe.get(i);
            for(int j=i+1; j<listaRighe.size();j++){
                String controllo = listaRighe.get(j);
                //controllo caratteri uguali tra le stringhe
                int count = 0;
                String str = "";
                for(int k = 0; k<riferimento.length();k++){
                    if(riferimento.charAt(k)== controllo.charAt(k)){
                        count++;
                        str = str+riferimento.charAt(k);
                    }
                }
                Pair pair = new Pair();
                pair.eq=count;
                pair.value1=riferimento;
                pair.value1=controllo;
                pair.valueEquals=str;
                mappa.put(riferimento+"|"+controllo, pair);
            }
        }
        Set<String> key= mappa.keySet();

        key.forEach(k->{
            if(mappa.get(k).eq == mappa.get(k).value1.length()-1){
                System.out.println("diff1="+k);
                System.out.println("streq="+mappa.get(k).valueEquals);
            }
        });


    }
    static class Pair {
        public Pair(){

        }
        public String value1;
        public String value2;
        public String valueEquals;
        public int eq;
    }
}