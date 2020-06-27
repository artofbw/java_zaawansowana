package pl.jazapp.app.webapp.category.edit;

import pl.jazapp.app.webapp.category.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class EditCategoryService {
    @PersistenceContext
    EntityManager em;

    @Transactional
    public void saveCategory(Category category) {
        if(category.getId() == null) {
            em.persist(category);
        } else {
            em.merge(category);
        }
    }

    public Category getCategoryById(Long categoryId) {
        return em.find(Category.class, categoryId);
    }
}
