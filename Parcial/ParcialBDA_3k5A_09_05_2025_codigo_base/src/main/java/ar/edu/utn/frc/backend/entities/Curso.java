package ar.edu.utn.frc.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String areaTematica;

    public Curso(String nombre, String areaTematica) {
        this.nombre = nombre;
        this.areaTematica = areaTematica;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAreaTematica() {
        return areaTematica;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAreaTematica(String areaTematica) {
        this.areaTematica = areaTematica;
    }

    @Override
    public String toString() {
        return String.format("Curso - %s (%s)", 
            nombre, 
            areaTematica);
    }

}
