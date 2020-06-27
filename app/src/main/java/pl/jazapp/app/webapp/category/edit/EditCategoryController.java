package pl.jazapp.app.webapp.category.edit;

import pl.jazapp.app.ParameterRetriever;
import pl.jazapp.app.webapp.department.DepartmentService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class EditCategoryController {
    private EditCategoryRequest editCategoryRequest;

    @Inject
    EditCategoryService editCategoryService;

    @Inject
    DepartmentService departmentService;

    @Inject
    ParameterRetriever parameterRetriever;

    public EditCategoryRequest getEditCategoryRequest() {
        if(editCategoryRequest == null) {
            if (parameterRetriever.contains("categoryId")) {
                var categoryId = parameterRetriever.getParameterAsLong("categoryId");
                var categoryEntity = editCategoryService.getCategoryById(categoryId);
                editCategoryRequest = new EditCategoryRequest(categoryEntity);
            } else {
                editCategoryRequest = new EditCategoryRequest();
            }
        }
        return editCategoryRequest;
    }

    public String save() {
        var section = departmentService.getDepartmentById(editCategoryRequest.getDepartmentId()).get();
        var category = editCategoryRequest.toCategoryEntity();
        category.setDepartment(section);
        editCategoryService.saveCategory(category);
        return "/categories/list.xhtml?faces-redirect=true";
    }
}
