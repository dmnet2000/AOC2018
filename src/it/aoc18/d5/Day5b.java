package it.aoc18.d5;


import com.sun.xml.internal.ws.util.StringUtils;
import it.aoc18.ReadFromFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day5b {

    public static void main(String[] args) throws IOException {

        ReadFromFile rf = new ReadFromFile("day5Input.txt");
        List<String> listaRighe = rf.getListaRighe();

//        listaRighe = new ArrayList<>();
        //      listaRighe.add("dabAcCaCBAcCcaDA");

        String str = listaRighe.get(0);
        System.out.println(str);
        Integer reduction = calcolaShortPolim(str);
        System.out.println("min = " + reduction);
    }

    private static Integer calcolaShortPolim(String str) {

        Map<Character, Integer> mappaLengh = new HashMap<>();
        int min = str.length();
        for (int i = 0; i < str.length(); i++) {

            char c = Character.toLowerCase(str.charAt(i));
            char c1 = Character.toUpperCase(str.charAt(i));
            if (mappaLengh.get(c) == null) {


                //System.out.println(String.valueOf(c));
                String str1 = replace(c, c1, str);
                String reduced = calacolaRiduzioneCiclica(str1);
                System.out.println("ch=" + c + " | length=" + str1.length());
                System.out.println(str1);
                System.out.println(reduced);
                System.out.println("Length = " + reduced.length());
                if (reduced.length() < min) {
                    min = reduced.length();
                }
                mappaLengh.put(c, reduced.length());
            }
        }

        return min;
    }

    private static String replace(Character value1, Character value2, String strComplete) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < strComplete.length(); i++) {
            if (strComplete.charAt(i) == value1 || strComplete.charAt(i) == value2) {
                continue;

            } else {
                sb.append(strComplete.charAt(i));
            }
        }
        return sb.toString();
    }


    /*
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
*/
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
