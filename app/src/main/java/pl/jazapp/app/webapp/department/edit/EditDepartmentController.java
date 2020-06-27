package pl.jazapp.app.webapp.department.edit;

import pl.jazapp.app.ParameterRetriever;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class EditDepartmentController {
    private EditDepartmentRequest editDepartmentRequest;

    @Inject
    EditDepartmentService editDepartmentService;

    @Inject
    ParameterRetriever parameterRetriever;

    public EditDepartmentRequest departmentRequest() {
        if(editDepartmentRequest == null) {
            if(parameterRetriever.contains("departmentId")) {
                var departmentId = parameterRetriever.getParameterAsLong("departmentId");
                var departmentEntity = editDepartmentService.getDepartmentById(departmentId);
                editDepartmentRequest = new EditDepartmentRequest(departmentEntity);
            } else {
                editDepartmentRequest = new EditDepartmentRequest();
            }
        }
        return editDepartmentRequest;
    }

    public String save() {
        editDepartmentService.saveDepartment(editDepartmentRequest.toDepartmentEntity());
        return "/departments/list.xhtml?faces-redirect=true";
    }
}
