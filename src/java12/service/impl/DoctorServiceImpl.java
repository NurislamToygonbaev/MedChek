package java12.service.impl;
import java12.dao.DoctorDao;
import java12.models.Doctor;
import java12.service.DoctorService;
import java12.service.GenericService;
import java.util.List;

public class DoctorServiceImpl implements DoctorService, GenericService<Doctor> {

    private final DoctorDao doctorDao;

    public DoctorServiceImpl(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }


    @Override
    public Doctor findDoctorById(Long id) {
        try {
            return doctorDao.findById(id);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        try {
            Boolean b = doctorDao.assignDoctorToDepartment(departmentId, doctorsId);
            if (b.equals(false)) System.out.println(false);
            return "Successfully assign doctor to Department";
        } catch (NotFoundException | IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        try {
            return doctorDao.getAllDoctorsByHospitalId(id);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        try {
            return doctorDao.getAllDoctorsByDepartmentId(id);
        } catch (NotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String add(Long hospitalId, Doctor doctor) {
        try {
            for (Doctor doctor1 : getAllDoctorsByHospitalId(hospitalId)) {
                if (doctor1.getId().equals(doctor.getId())){
                    throw new IllegalArgumentException("error");
                }
            }
            doctorDao.add(hospitalId, doctor);
            return "Successfully added";
        } catch (NotFoundException | IllegalArgumentException e){
            return e.getMessage();
        }
    }

    @Override
    public String removeById(Long id) {
        try {
            Boolean remove = doctorDao.remove(id);
            if (remove.equals(false)) System.out.println(false);
            return "Successfully deleted";
        } catch (NotFoundException e){
            return e.getMessage();
        }
    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        try {
            Boolean b = doctorDao.updateById(id, doctor);
            if (b.equals(false)) System.out.println(false);
            return "Successfully updated";
        } catch (NotFoundException e){
            return e.getMessage();
        }
    }
}
