package java12.dao;
import java12.models.Department;
import java.util.List;

public interface DepartmentDao {

    Boolean add(Long hospitalId, Department department);

    Boolean remove(Long id);

    List<Department> getAll(Long hospitalId);

    List<Department> getAllDepartments();

    Boolean update(Long id, Department department);
}
