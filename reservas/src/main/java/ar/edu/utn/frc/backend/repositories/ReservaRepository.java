package ar.edu.utn.frc.backend.repositories;

import ar.edu.utn.frc.backend.entities.Reserva;
import jakarta.persistence.*;

public class ReservaRepository {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ReservasBD");

    public void save(Reserva reserva) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(reserva);
        em.getTransaction().commit();
        em.close();
    }

    public Reserva find(String reservaId) {
        EntityManager em = emf.createEntityManager();
        Reserva reserva = em.find(Reserva.class, reservaId);
        em.close();
        return reserva;
    }
}
