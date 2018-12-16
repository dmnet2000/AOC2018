package it.aoc18;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFromFile {
    private String file;


    public ReadFromFile(String fileNAme) {
        this.file = fileNAme;
    }

    public List<String> getListaRighe() throws IOException {

        File f = new File(file);

        BufferedReader br = new BufferedReader(new FileReader(f));

        String line = "";
        List<String> listaRighe = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            listaRighe.add(line);
        }

        return listaRighe;
    }
}
