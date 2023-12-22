package java12.dao.impl;

import java12.MyGeneratorId;
import java12.dao.DepartmentDao;
import java12.database.DataBase;
import java12.models.Department;
import java12.models.Hospital;
import java12.service.impl.NotFoundException;

import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {

    private final DataBase dataBase;

    public DepartmentDaoImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public Boolean add(Long hospitalId, Department department) {
        for (Hospital hospital : dataBase.getAll()) {
            if (hospital.getId().equals(hospitalId)) {
                department.setId(MyGeneratorId.generatorDepartment());
                return hospital.getDepartments().add(department);
            }
        }
        throw new NotFoundException("Hospital with " + hospitalId + " not found!");
    }

    @Override
    public Boolean remove(Long id) {
        for (Hospital hospital : dataBase.getAll()) {
            return hospital.getDepartments().removeIf(department -> department.getId().equals(id));
        }
        throw new NotFoundException("Department with " + id + " not found!");
    }

    @Override
    public List<Department> getAll(Long hospitalId) {
        for (Hospital hospital : dataBase.getAll()) {
            if (hospital.getId().equals(hospitalId)) {
                return hospital.getDepartments();
            }
        }
        throw new NotFoundException("Hospital with " + hospitalId + " not found!");
    }

    @Override
    public List<Department> getAllDepartments() {
        for (Hospital hospital : dataBase.getAll()) {
            return hospital.getDepartments();
        }
        throw new IllegalArgumentException("Department is empty");
    }

    @Override
    public Boolean update(Long id, Department department) {
        for (Hospital hospital : dataBase.getAll()) {
            for (Department hospitalDepartment : hospital.getDepartments()) {
                if (hospitalDepartment.getId().equals(id)) {
                    hospitalDepartment.setDepartmentName(department.getDepartmentName());
                    return true;
                }
            }
        }
        throw new NotFoundException("Hospital with " + id + " not found!");
    }


}
