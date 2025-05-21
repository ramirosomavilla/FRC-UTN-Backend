package ar.edu.utn.frc.backend.repositories;

import ar.edu.utn.frc.backend.entities.Juego;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JuegosRepository {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("VideojuegoDB");

    public void save(Juego juego) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(juego);
        em.getTransaction().commit();
        em.close();
    }

    public Juego find(Long juegoId) {
        EntityManager em = emf.createEntityManager();
        Juego juego = em.find(Juego.class, juegoId);
        em.close();
        return juego;
    }
}
