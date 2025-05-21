package ar.edu.utn.frc.backend.entities;

import jakarta.persistence.*;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteId;

    private String nombre;

    public Cliente() {}

    public Cliente(String nombre) {
        this.nombre = nombre;
    }

    // Getters y setters
    public Long getClienteId() {
        return clienteId;
    }

    public String getNombre() {
        return nombre;
    }
}
