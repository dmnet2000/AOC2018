package it.aoc18.d5;

import it.aoc18.ReadFromFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day5a {

    public static void main(String[] args) throws IOException {

        ReadFromFile rf = new ReadFromFile("day5Input.txt");
        List<String> listaRighe = rf.getListaRighe();

        //listaRighe = new ArrayList<>();
        //listaRighe.add("dabAcCaCBAcCcaDA");

        String str = listaRighe.get(0);
        System.out.println(str);
        String reduction = calacolaRiduzioneCiclica(str);
        System.out.println(reduction);
    }

    private static String calacolaRiduzione(String str) {

        boolean ridotto = false;
        int index = 0;
        while (index < str.length() - 1) {
            char first = str.charAt(index);
            char second = str.charAt(index + 1);
            if (Character.toUpperCase(first) == Character.toUpperCase(second) && first != second) {
                System.out.println("coppia = " + first + "," + second);
                //caratteri uguali bisogna capire se sono opposti
                ridotto = true;
                break;
            }
            index++;
        }
        if (ridotto) {
            //rimuovo i caratteri da ridurre

            System.out.println("RIDUCO");
            StringBuilder sb = new StringBuilder(str);
            sb.replace(index, index + 2, "");
            str = sb.toString();
            sb = null;
            System.out.println(str);
            System.out.println(index);
            return calacolaRiduzione(str);
        }
        return str;
    }

    private static String calacolaRiduzioneCiclica(String value) {
        String str = value;
        boolean ridotto = false;
        while (!ridotto) {
            int index = 0;
            boolean rid = false;
            while (index < str.length() - 1) {
                char first = str.charAt(index);
                char second = str.charAt(index + 1);
                if (Character.toUpperCase(first) == Character.toUpperCase(second) && first != second) {
                    System.out.println("coppia = " + first + "," + second);
                    //caratteri uguali bisogna capire se sono opposti
                    rid = true;
                    break;
                }
                index++;
            }
            if (rid) {
                //rimuovo i caratteri da ridurre

                System.out.println("RIDUCO");
                StringBuilder sb = new StringBuilder(str);
                sb.deleteCharAt(index);
                sb.deleteCharAt(index);
                str = sb.toString();
                sb = null;
                System.out.println(str);
                System.out.println(index);
                //return calacolaRiduzione(str);
            } else {
                ridotto = true;
            }
            System.out.println("Lunghezza=" + str.length());
        }
        return str;
    }

}