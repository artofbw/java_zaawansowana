package pl.jazapp.app.webapp.category;

import pl.jazapp.app.webapp.department.Department;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class CategoryRepository {
    @PersistenceContext
    private EntityManager em;

    public List<Category> fetchAll() {
        Query query = em.createQuery("FROM Category ", Category.class);
        return query.getResultList();
    }
}
