package ar.edu.utn.frc.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Juego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long juegosId;

    private String nombre;
    private String genero;

    public Juego() {}

    public Juego(long juegoId, String nombre, String genero) {
        this.juegosId = juegoId;
        this.nombre = nombre;
        this.genero = genero;
    }

    public long getJuegosId() {
        return juegosId;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Juego{" +
                "juegosId=" + juegosId +
                ", nombre='" + nombre + '\'' +
                ", genero='" + genero + '\'' +
                '}';
    }
}
