package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorLibros {

    private ArrayList<Cliente> clientes;
    private String directorioGuardado;

    public GestorLibros() {
        this.clientes = new ArrayList<>();
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.directorioGuardado = ".\\inventario_y_vencimientos_" + hoy.format(formatoFecha) + ".txt";
    }

    public void lectorEntrada() {
        File fileInput = new File(".\\Biblioteca Nacional de España.txt");
        Scanner reader = null;
        try {
            reader = new Scanner(fileInput);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String data = "";

        while (reader.hasNextLine()) {
            data += reader.nextLine() + "\n";
        }
        reader.close();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (int i = 0; i < data.split("\n").length / 12; i++) {
            String nombreL = data.split("\n")[12 * i + 1];
            String autorL = data.split("\n")[12 * i + 3];
            String fechaSolicitudL = data.split("\n")[12 * i + 5];
            String fechaEntregaEstipuladaL = data.split("\n")[12 * i + 7];
            String estadoL = data.split("\n")[12 * i + 9];
            int dni = Integer.parseInt(data.split("\n")[12 * i + 11]);

            LocalDate fsl = LocalDate.parse(fechaSolicitudL, formato);
            LocalDate feel = LocalDate.parse(fechaEntregaEstipuladaL, formato);

            LibroPrestado lp = new LibroPrestado(nombreL, autorL, fsl, feel, estadoL);
            Cliente c = new Cliente(dni, lp);
            this.clientes.add(c);

        }
    }

    public void guardarTxt() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.directorioGuardado));
            for (Cliente c : this.clientes) {
                bw.write(c.getLibro().toString());
                bw.write(c.toString());
                bw.write("\n-----------------------------------------------\n");
            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
