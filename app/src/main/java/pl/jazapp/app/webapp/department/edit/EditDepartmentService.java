package pl.jazapp.app.webapp.department.edit;

import pl.jazapp.app.webapp.department.Department;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class EditDepartmentService {
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

    public Department getDepartmentById(Long departmentId) {
        return em.find(Department.class, departmentId);
    }
}
