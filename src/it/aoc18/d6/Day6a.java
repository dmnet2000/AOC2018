package it.aoc18.d6;

import it.aoc18.ReadFromFile;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day6a {
    public static void main(String[] args) throws IOException {

        ReadFromFile rf = new ReadFromFile("day6Input.txt");
        List<String> listaRighe = rf.getListaRighe();


        String[] lista =
                {"1, 1",
                        "1, 6",
                        "8, 3",
                        "3, 4",
                        "5, 5",
                        "8, 9" };
        listaRighe = new ArrayList<>();
        for (int i = 0; i < lista.length; i++) {
            listaRighe.add(lista[i]);
        }


        //      listaRighe.add("dabAcCaCBAcCcaDA");
        List<Coordinate> listaCoordinate = getListaCoordinate(listaRighe);

        listaCoordinate.forEach(coordinate -> System.out.println("x=" + coordinate.x + "| y=" + coordinate.y));
    }


    private static List<Coordinate> getListaCoordinate(List<String> listaRighe) {
        List<Coordinate> listCoordinate = new ArrayList<>();

        listaRighe.forEach(line -> {
            Coordinate coordinata = new Coordinate();
            coordinata.x = Integer.parseInt(line.substring(0, line.indexOf(",")));
            coordinata.y = Integer.parseInt(line.substring(line.indexOf(",") + 2, line.length()));
            listCoordinate.add(coordinata);
        });
        return listCoordinate;
    }

    static class Coordinate {
        int x;
        int y;

    }
}