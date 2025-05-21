package ar.edu.utn.frc.backend.entities;

import jakarta.persistence.*;

@Entity
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long habitacionId;

    private Long numero;

    public Habitacion() {}

    public Habitacion(Long numero) {
        this.numero = numero;
    }

    // Getters y setters
    public Long getHabitacionId() {
        return habitacionId;
    }

    public Long getNumero() {
        return numero;
    }
}
