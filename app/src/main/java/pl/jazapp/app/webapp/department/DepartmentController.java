package pl.jazapp.app.webapp.department;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class DepartmentController {
    @Inject
    DepartmentRepository departmentRepository;

    public List<Department> getDepartmentList() {
        return departmentRepository.fetchAll();
    }
}
