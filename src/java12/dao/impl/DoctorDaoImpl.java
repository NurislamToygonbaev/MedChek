package java12.dao.impl;

import java12.dao.DoctorDao;
import java12.database.DataBase;
import java12.models.Department;
import java12.models.Doctor;
import java12.models.Hospital;
import java12.service.impl.NotFoundException;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao {

    private final DataBase dataBase;

    public DoctorDaoImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public Doctor findById(Long doctorId) {
        for (Hospital hospital : dataBase.getAll()) {
            for (Doctor doctor : hospital.getDoctors()) {
                if (doctor.getId().equals(doctorId)) {
                    return doctor;
                }
            }
        }
        throw new NotFoundException("Doctor with " + doctorId + " not found!");
    }

    @Override
    public Boolean add(Long hospitalId, Doctor doctor) {
        for (Hospital hospital : dataBase.getAll()) {
            if (hospital.getId().equals(hospitalId)){
                return hospital.getDoctors().add(doctor);
            }
        }
        throw new NotFoundException("Hospital with " + hospitalId + " not found!");
    }

    @Override
    public Boolean remove(Long id) {
        for (Hospital hospital : dataBase.getAll()) {
            for (Doctor doctor : hospital.getDoctors()) {
                if (doctor.getId().equals(id)){
                   return hospital.getDoctors().remove(doctor);
                }
            }
        }
        throw new NotFoundException("Doctor with " + id + " not found!");
    }

    @Override
    public List<Doctor> getAll() {
        for (Hospital hospital : dataBase.getAll()) {
            return hospital.getDoctors();
        }
        throw new NotFoundException("Doctors is empty");
    }

    @Override
    public Boolean assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        for (Hospital hospital : dataBase.getAll()) {
            for (Department department : hospital.getDepartments()) {
                if (departmentId.equals(department.getId())) {
                    for (Doctor doctor : hospital.getDoctors()) {
                        if (doctorsId.contains(doctor.getId())){
                           return department.getDoctors().add(doctor);
                        }
                    }
                    throw new IllegalArgumentException("Doctors with "+doctorsId+" not found!");
                }
            }
        }
        throw new NotFoundException("Department with " + departmentId + " not found");
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        for (Hospital hospital : dataBase.getAll()) {
            if (hospital.getId().equals(id)) {
                return hospital.getDoctors();
            }
        }
        throw new NotFoundException("Hospital with " + id + " not found");
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        for (Hospital hospital : dataBase.getAll()) {
            for (Department department : hospital.getDepartments()) {
                if (department.getId().equals(id)) {
                    return department.getDoctors();
                }
            }
        }
        throw new NotFoundException("Department with " + id + " not found");
    }

    @Override
    public Boolean updateById(Long id, Doctor doctor) {
        for (Hospital hospital : dataBase.getAll()) {
            for (Doctor hospitalDoctor : hospital.getDoctors()) {
                if (hospitalDoctor.getId().equals(id)) {
                    hospitalDoctor.setFirstName(doctor.getFirstName());
                    hospitalDoctor.setLastName(doctor.getLastName());
                    hospitalDoctor.setGender(doctor.getGender());
                    hospitalDoctor.setExperienceYear(doctor.getExperienceYear());
                    return true;
                }
            }
        }
        throw new NotFoundException("Doctor with " + id + " not found");
    }
}
