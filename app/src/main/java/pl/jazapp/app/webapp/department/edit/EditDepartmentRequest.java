package pl.jazapp.app.webapp.department.edit;

import pl.jazapp.app.webapp.department.Department;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class EditDepartmentRequest {
    private Long id;
    private String name;

    public Department toDepartmentEntity() {
        var department = new Department();
        department.setId(id);
        department.setName(name);
        return department;
    }

    public EditDepartmentRequest(Department department) {
        this.id = department.getId();
        this.name = department.getName();
    }

    public EditDepartmentRequest() {}

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
}
