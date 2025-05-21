package ar.edu.utn.frc.backend.repositories;

import ar.edu.utn.frc.backend.entities.Cliente;
import jakarta.persistence.*;

public class ClienteRepository {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ReservasBD");

    public void save(Cliente cliente) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        em.close();
    }

    public Cliente find(Long clienteId) {
        EntityManager em = emf.createEntityManager();
        Cliente cliente = em.find(Cliente.class, clienteId);
        em.close();
        return cliente;
    }
}
