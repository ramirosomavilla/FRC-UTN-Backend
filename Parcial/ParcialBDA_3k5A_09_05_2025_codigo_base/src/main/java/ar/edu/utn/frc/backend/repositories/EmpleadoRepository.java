package ar.edu.utn.frc.backend.repositories;

import ar.edu.utn.frc.backend.entities.Empleado;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EmpleadoRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("CapacitacionesDB");

    public void save(Empleado empleado) {
        var em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(empleado);
        em.getTransaction().commit();
        em.close();
    }

    public Empleado findById(int id) {
        var em = emf.createEntityManager();
        var empleado = em.find(Empleado.class, id);
        em.close();
        return empleado;
    }
}
