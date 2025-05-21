package ar.edu.utn.frc.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long partidaId; // Identificador único de la partida
        private long jugadorId; // Identificador único del jugador
        private String jugadorNombre; // Nombre del jugador
        private long juegoId; // Identificador único del juego
        private String juegoNombre; // Nombre del juego
        private String genero; // Género del juego (por ejemplo, Acción, Estrategia, Rol, Deportes)
        private LocalDate fechaPartida; // Fecha en que se jugó la partida
        private int duracionMinutos; // Duración total de la partida en minutos
        private int puntuacion; // Puntuación obtenida por el jugador
        private String estado; // Estado de la partida (Completada, En curso, Abandonada)

    public Partida(long partidaId, long jugadorId, String jugadorNombre, long juegoId, String juegoNombre, String genero,
                   LocalDate fechaPartida, int duracionMinutos, int puntuacion, String estado) {
        this.partidaId = partidaId;
        this.jugadorId = jugadorId;
        this.jugadorNombre = jugadorNombre;
        this.juegoId = juegoId;
        this.juegoNombre = juegoNombre;
        this.genero = genero;
        this.fechaPartida = fechaPartida;
        this.duracionMinutos = duracionMinutos;
        this.puntuacion = puntuacion;
        this.estado = estado;
    }

    public long getPartidaId() {
        return partidaId;
    }

    public long getJugadorId(){
        return jugadorId;
    }

    public String getEstado(){
        return estado;
    }

    public String getGenero(){
        return genero;
    }

    public Integer getPuntuacion(){
        return puntuacion;
    }

    public String getJugadorNombre(){
        return jugadorNombre;
    }

    public Partida(){}
    @Override
    public String toString(){
        return "Partida{" +
                "partidaId=" + partidaId +
                ", jugadorId=" + jugadorId +
                ", jugadorNombre='" + jugadorNombre + '\'' +
                ", juegoId=" + juegoId +
                ", juegoNombre='" + juegoNombre + '\'' +
                ", genero='" + genero + '\'' +
                ", fechaPartida=" + fechaPartida +
                ", duracionMinutos=" + duracionMinutos +
                ", puntuacion=" + puntuacion +
                ", estado='" + estado + '\'' +
                '}';
    }
}
