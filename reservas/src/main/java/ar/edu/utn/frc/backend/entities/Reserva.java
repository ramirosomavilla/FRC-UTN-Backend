package ar.edu.utn.frc.backend.entities;

import jakarta.persistence.*;

@Entity
public class Reserva {
    @Id
    private String reservaId;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Habitacion habitacion;

    private String fechaInicio;
    private String fechaFin;
    private double totalPagado;
    private String estado;

    public Reserva() {}

    public Reserva(String reservaId, Cliente cliente, Habitacion habitacion, String fechaInicio, String fechaFin, double totalPagado, String estado) {
        this.reservaId = reservaId;
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.totalPagado = totalPagado;
        this.estado = estado;
    }

    // Getters y setters
    public String getReservaId() {
        return reservaId;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public double getTotalPagado() {
        return totalPagado;
    }

    public String getEstado() {
        return estado;
    }
}
