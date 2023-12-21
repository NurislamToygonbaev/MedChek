package java12.dao;
import java12.models.Doctor;
import java.util.List;

public interface DoctorDao {

    Doctor findById(Long doctorId);

    Boolean add(Long hospitalId, Doctor doctor);

    Boolean remove(Long id);

    List<Doctor> getAll();

    Boolean assignDoctorToDepartment(Long departmentId, List<Long> doctorsId);

    List<Doctor> getAllDoctorsByHospitalId(Long id);

    List<Doctor> getAllDoctorsByDepartmentId(Long id);

    Boolean updateById(Long id, Doctor doctor);
}
