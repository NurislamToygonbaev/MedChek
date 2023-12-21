package java12.dao.impl;

import java12.dao.PatientDao;
import java12.database.DataBase;
import java12.models.Hospital;
import java12.models.Patient;
import java12.service.impl.NotFoundException;

import java.util.*;

public class PatientDaoImpl implements PatientDao {

    private final DataBase dataBase;

    public PatientDaoImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public Patient findById(Long patientId) {
        for (Hospital hospital : dataBase.getAll()) {
            for (Patient patient : hospital.getPatients()) {
                if (patient.getId().equals(patientId)){
                    return patient;
                }
            }
        }
        throw new NotFoundException("Patient with "+patientId+" not found!");
    }

    @Override
    public Boolean add(Long hospitalId, Patient patient) {
        for (Hospital hospital : dataBase.getAll()) {
            for (Patient hospitalPatient : hospital.getPatients()) {
                if (hospitalPatient.getId().equals(hospitalId)){
                    throw new IllegalArgumentException("error");
                }
            }
            if (hospital.getId().equals(hospitalId)){
               return hospital.getPatients().add(patient);
            }
        }
        throw new NotFoundException("Hospital with "+hospitalId+" not found!");
    }

    @Override
    public Boolean delete(Long id) {
        for (Hospital hospital : dataBase.getAll()) {
           return hospital.getPatients().removeIf(patient -> patient.getId().equals(id));
        }
        throw new NotFoundException("Patient with "+id+" not found!");
    }

    @Override
    public List<Patient> getAll() {
        for (Hospital hospital : dataBase.getAll()) {
            return hospital.getPatients();
        }
        throw new NotFoundException("Patients is empty!");
    }

    @Override
    public Boolean updateById(Long id, Patient patient) {
        for (Hospital hospital : dataBase.getAll()) {
            for (Patient hospitalPatient : hospital.getPatients()) {
                if (hospitalPatient.getId().equals(id)){
                    hospitalPatient.setFirstName(patient.getFirstName());
                    hospitalPatient.setLastName(patient.getLastName());
                    hospitalPatient.setAge(patient.getAge());
                    hospitalPatient.setGender(patient.getGender());
                    return true;
                }
            }
        }
        throw new NotFoundException("Patient with " + id + " not found");
    }

    @Override
    public Boolean addPatientsToHospital(Long id, List<Patient> patients) {
        for (Hospital hospital : dataBase.getAll()) {
            if (hospital.getId().equals(id)){
               return hospital.getPatients().addAll(patients);
            }
        }
        throw new NotFoundException("Hospital with " + id + " not found");
    }

    @Override
    public Map<Integer, List<Patient>> getPatientByAge() {
        Map<Integer, List<Patient>> patientMap = new HashMap<>();
        for (Hospital hospital : dataBase.getAll()) {
            for (Patient patient : hospital.getPatients()) {
                int age = patient.getAge();
                if (!patientMap.containsKey(age)){
                    patientMap.put(age, new ArrayList<>());
                }
                patientMap.get(age).add(patient);
            }
        }
        return patientMap;
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        List<Patient> patients = new ArrayList<>();
        for (Hospital hospital : dataBase.getAll()) {
            patients.addAll(hospital.getPatients());
        }
        if (ascOrDesc.equalsIgnoreCase("asc")) {
            Comparator<Patient> comparator = Comparator.comparing(Patient::getAge);
            patients.sort(comparator);
            return patients;
        } else if (ascOrDesc.equalsIgnoreCase("desc")) {
            Comparator<Patient> comparator = Comparator.comparing(Patient::getAge).reversed();
            patients.sort(comparator);
            return patients;
        }
        throw new NotFoundException("enter only asc or desc. you write: " + ascOrDesc);
    }
}
