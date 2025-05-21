package ar.edu.utn.frc.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long jugadorId;

    private String nombre;

    public Jugador(){};

    public Jugador(long id, String nombre) {this.jugadorId = id; this.nombre = nombre;}

    public long getId() {
        return jugadorId;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "jugadorId=" + jugadorId +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
