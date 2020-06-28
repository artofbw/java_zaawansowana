package pl.jazapp.app.repository;

import pl.jazapp.app.webapp.auction.Auction;
import pl.jazapp.app.webapp.department.Department;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DepartmentRepository {
    @PersistenceContext
    private EntityManager em;

    public List<Department> fetchAll() {
        Query query = em.createQuery("FROM Department", Department.class);
        return query.getResultList();
    }

    public Department fetchRow(Long id) {
        Query query = em.createQuery("FROM Department WHERE id = ?1", Department.class);
        query.setParameter(1, id);
        return (Department) query.getSingleResult();
    }

    public Optional<Department> fetchByName(String name) {
        TypedQuery<Department> query = em.createQuery("FROM Department WHERE name = ?1", Department.class);
        query.setParameter(1, name);
        return query.getResultStream().findAny();
    }

    @Transactional
    public Department save(Department department){
        if(department.getId() == null) {
            em.persist(department);
        } else {
            department = em.merge(department);
        }
        return department;
    }

}
