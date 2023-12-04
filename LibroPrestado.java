package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class LibroPrestado extends Libro implements ILibroPrestado {
    private LocalDate fechaSolicitud;
    private LocalDate fechaEntregaEstipulada;
    private String estadoPrestamo;

    public LibroPrestado(String nombre, String autor, LocalDate fechaSolicitud, LocalDate fechaEntregaEstipulada, String estadoPrestamo) {
        super(nombre, autor);
        this.fechaSolicitud = fechaSolicitud;
        this.fechaEntregaEstipulada = fechaEntregaEstipulada;
        //Cuando se crea el libro ningun prestamo está vencido
        this.estadoPrestamo = estadoPrestamo;
    }

    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public LocalDate getFechaEntregaEstipulada() {
        return fechaEntregaEstipulada;
    }

    public void setFechaEntregaEstipulada(LocalDate fechaEntregaEstipulada) {
        this.fechaEntregaEstipulada = fechaEntregaEstipulada;
    }

    public String getEstadoPrestamo() {
        return estadoPrestamo;
    }

    public void setEstadoPrestamo(String estadoPrestamo) {
        this.estadoPrestamo = estadoPrestamo;
    }

    @Override
    public long calcularDiasAtraso() {
        return ChronoUnit.DAYS.between(this.fechaSolicitud, this.fechaEntregaEstipulada);
    }

    @Override
    public int calcularDeuda() {
        return Math.toIntExact(this.calcularDiasAtraso());
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Libro: " + this.getNombre() +
                "\nAutor: "+ this.getAutor() +
                "\nFecha Solicitud: "+this.fechaSolicitud.format(formato) +
                "\nFecha de Entrega Estipulada: "+this.fechaEntregaEstipulada.format(formato);
    }
}
