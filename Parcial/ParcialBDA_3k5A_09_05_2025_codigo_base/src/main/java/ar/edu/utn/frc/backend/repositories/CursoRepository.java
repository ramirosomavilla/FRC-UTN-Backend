package ar.edu.utn.frc.backend.repositories;

import ar.edu.utn.frc.backend.entities.Curso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CursoRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("CapacitacionesDB");

    public void save(Curso curso) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(curso);
        em.getTransaction().commit();
        em.close();
    }

    public Curso findById(int id) {
        EntityManager em = emf.createEntityManager();
        Curso curso = em.find(Curso.class, id);
        em.close();
        return curso;
    }
}
