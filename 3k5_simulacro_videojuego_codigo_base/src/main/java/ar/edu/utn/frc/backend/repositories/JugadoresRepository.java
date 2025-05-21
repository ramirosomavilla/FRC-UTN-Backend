package ar.edu.utn.frc.backend.repositories;

import ar.edu.utn.frc.backend.entities.Jugador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JugadoresRepository {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("VideojuegoDB");

    public void save(Jugador jugador) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(jugador);
        em.getTransaction().commit();
        em.close();
    }

    public Jugador find(Long jugadorId) {
        EntityManager em = emf.createEntityManager();
        Jugador jugador = em.find(Jugador.class, jugadorId);
        em.close();
        return jugador;
    }
}
