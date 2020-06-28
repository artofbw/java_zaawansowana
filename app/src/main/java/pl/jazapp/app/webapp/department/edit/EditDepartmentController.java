package pl.jazapp.app.webapp.department.edit;

import pl.jazapp.app.ParameterRetriever;
import pl.jazapp.app.services.DepartmentService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class EditDepartmentController {
    private EditDepartmentRequest editDepartmentRequest;

    @Inject
    DepartmentService departmentService;

    @Inject
    ParameterRetriever parameterRetriever;

    public EditDepartmentRequest departmentRequest() {
        if(editDepartmentRequest == null) {
            if(parameterRetriever.contains("departmentId")) {
                var departmentId = parameterRetriever.getParameterAsLong("departmentId");
                var departmentEntity = departmentService.getDepartmentById(departmentId);
                editDepartmentRequest = new EditDepartmentRequest(departmentEntity);
            } else {
                editDepartmentRequest = new EditDepartmentRequest();
            }
        }
        return editDepartmentRequest;
    }

    public String save() {
        departmentService.saveDepartment(editDepartmentRequest.toDepartmentEntity());
        return "/departments/list.xhtml?faces-redirect=true";
    }
}
