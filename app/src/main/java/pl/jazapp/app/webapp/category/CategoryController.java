package pl.jazapp.app.webapp.category;

import pl.jazapp.app.repository.CategoryRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class CategoryController {
    @Inject
    CategoryRepository categoryRepository;

    public List<Category> getCategoryList() {
        return categoryRepository.fetchAll();
    }
}
