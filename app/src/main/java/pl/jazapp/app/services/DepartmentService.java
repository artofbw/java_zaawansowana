package pl.jazapp.app.services;

import pl.jazapp.app.webapp.department.Department;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class DepartmentService {
    @PersistenceContext
    EntityManager em;

    @Transactional
    public void saveDepartment(Department department) {
        if (department.getId() == null) {
            em.persist(department);
        } else {
            em.merge(department);
        }
    }
    public Department getDepartmentById(Long id) {
        return em.find(Department.class, id);
    }
}
