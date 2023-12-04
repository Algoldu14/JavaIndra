package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Cliente {
    private int dni;
    private int deuda;
    private boolean moroso;
    private LibroPrestado libro;

    public Cliente(int dni,LibroPrestado libro) {
        this.dni = dni;
        this.deuda = 0;
        this.moroso = false;
        this.libro = libro;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getDeuda() {
        return deuda;
    }

    public void setDeuda(int deuda) {
        this.deuda = deuda;
    }

    public boolean isMoroso() {
        return moroso;
    }

    public void setMoroso(boolean moroso) {
        this.moroso = moroso;
    }

    public LibroPrestado getLibro() {
        return libro;
    }

    public void setLibro(LibroPrestado libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return "\nDNI Cliente: " + this.dni +
                "\nDías de atraso: " + this.libro.calcularDiasAtraso() +
                "\nEstado del prestamo: " + (this.libro.calcularDeuda() > 0 ? "Vencido." : "En regla") +
                "\nDinero adeudado: " + this.libro.calcularDeuda() +
                "\nEs moroso?: " + (this.libro.calcularDeuda() > 0 ? "SI." : "NO") ;
    }
}
