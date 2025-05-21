package ar.edu.utn.frc.backend.repositories;

import ar.edu.utn.frc.backend.entities.Habitacion;
import jakarta.persistence.*;

public class HabitacionRepository {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ReservasBD");

    public void save(Habitacion habitacion) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(habitacion);
        em.getTransaction().commit();
        em.close();
    }

    public Habitacion find(Long habitacionId) {
        EntityManager em = emf.createEntityManager();
        Habitacion habitacion = em.find(Habitacion.class, habitacionId);
        em.close();
        return habitacion;
    }
}
