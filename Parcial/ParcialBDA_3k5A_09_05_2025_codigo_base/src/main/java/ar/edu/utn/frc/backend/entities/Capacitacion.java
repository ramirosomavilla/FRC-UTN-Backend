package ar.edu.utn.frc.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Capacitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Empleado empleado;
    @ManyToOne
    private Curso curso;
    private LocalDate fechaCapacitacion;
    private int duracionHoras;
    private double calificacionFinal;
    private String estado;

    public Capacitacion(int id, Empleado empleado, Curso curso, double calificacionFinal, LocalDate fechaCapacitacion, int duracionHoras, String estado) {
        this.id = id;
        this.empleado = empleado;
        this.curso = curso;
        this.calificacionFinal = calificacionFinal;
        this.fechaCapacitacion = fechaCapacitacion;
        this.duracionHoras = duracionHoras;
        this.estado = estado;
    }

    public Capacitacion(Empleado empleado, Curso curso, double calificacionFinal, LocalDate fechaCapacitacion, int duracionHoras, String estado) {
        this.empleado = empleado;
        this.curso = curso;
        this.calificacionFinal = calificacionFinal;
        this.fechaCapacitacion = fechaCapacitacion;
        this.duracionHoras = duracionHoras;
        this.estado = estado;
    }

    public Capacitacion() {
    }

    public int getId() {
        return id;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public Curso getCurso() {
        return curso;
    }

    public double getCalificacionFinal() {
        return calificacionFinal;
    }

    public LocalDate getFechaCapacitacion() {
        return fechaCapacitacion;
    }

    public int getDuracionHoras() {
        return duracionHoras;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public void setCalificacionFinal(double calificacionFinal) {
        this.calificacionFinal = calificacionFinal;
    }

    public void setFechaCapacitacion(LocalDate fechaCapacitacion) {
        this.fechaCapacitacion = fechaCapacitacion;
    }

    public void setDuracionHoras(int duracionHoras) {
        this.duracionHoras = duracionHoras;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    @Override
    public String toString() {
        return String.format("""
                Capacitación:
                    ├── %s
                    ├── Curso: %s
                    ├── Calificación Final: %.1f
                    ├── Fecha: %s
                    ├── Duración: %d horas
                    └── Estado: %s""",
                empleado,
                curso,
                calificacionFinal,
                fechaCapacitacion,
                duracionHoras,
                estado);
    }
}
