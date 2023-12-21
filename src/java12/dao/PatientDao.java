package java12.dao;
import java12.models.Patient;
import java.util.List;
import java.util.Map;

public interface PatientDao {

    Patient findById(Long patientId);

    Boolean add(Long hospitalId, Patient patient);

    Boolean delete(Long id);

    List<Patient> getAll();

    Boolean updateById(Long id, Patient patient);

    Boolean addPatientsToHospital(Long id, List<Patient> patients);

    Map<Integer, List<Patient>> getPatientByAge();

    List<Patient> sortPatientsByAge(String ascOrDesc);
}
