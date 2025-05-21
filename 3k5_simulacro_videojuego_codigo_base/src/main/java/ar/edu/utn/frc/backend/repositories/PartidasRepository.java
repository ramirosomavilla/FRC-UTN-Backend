package ar.edu.utn.frc.backend.repositories;

import ar.edu.utn.frc.backend.entities.Jugador;
import ar.edu.utn.frc.backend.entities.Partida;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PartidasRepository {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("VideojuegoDB");

    public void save(Partida partida) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(partida);
        em.getTransaction().commit();
        em.close();
    }

    public Partida find(Long partidaId) {
        EntityManager em = emf.createEntityManager();
        Partida partida = em.find(Partida.class, partidaId);
        em.close();
        return partida;
    }
}
