package pl.jazapp.app.webapp.category.edit;

import pl.jazapp.app.webapp.category.Category;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class EditCategoryRequest {
    private Long id;
    private String name;
    private Long departmentId;

    public EditCategoryRequest(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.departmentId = category.getDepartment().getId();
    }

    public EditCategoryRequest() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Category toCategoryEntity() {
        var category = new Category();
        category.setId(id);
        category.setName(name);
        return category;
    }
}
