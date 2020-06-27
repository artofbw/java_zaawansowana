package pl.jazapp.app.webapp.department;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@ApplicationScoped
public class DepartmentService {
    @PersistenceContext
    EntityManager em;

    public Optional<Department> getDepartmentById(Long id) {
        return Optional.ofNullable(em.find(Department.class, id));
    }
}
