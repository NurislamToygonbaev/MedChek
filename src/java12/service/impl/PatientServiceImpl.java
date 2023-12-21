package java12.service.impl;
import java12.dao.PatientDao;
import java12.models.Patient;
import java12.service.GenericService;
import java12.service.PatientService;

import java.util.*;

public class PatientServiceImpl implements PatientService, GenericService<Patient> {

    private final PatientDao dao;

    public PatientServiceImpl(PatientDao dao) {
        this.dao = dao;
    }

    @Override
    public String add(Long hospitalId, Patient patient) {
        try {
            dao.add(hospitalId, patient);
            return "Successfully added";
        } catch (NotFoundException | IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @Override
    public String removeById(Long id) {
        try {
            dao.delete(id);
            return "Successfully deleted";
        } catch (NotFoundException e) {
            return e.getMessage();
        }
    }

    @Override
    public String updateById(Long id, Patient patient) {
        try {
            dao.updateById(id, patient);
            return "Successfully updated";
        } catch (NotFoundException e) {
            return e.getMessage();
        }
    }

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        try {
            for (Patient patient : patients) {
                for (Patient patient1 : dao.getAll()) {
                    if (patient1.getId().equals(patient.getId())){
                        throw new IllegalArgumentException("error");
                    }
                }
            }
            dao.addPatientsToHospital(id, patients);
            return "Successfully added Patients";
        } catch (NotFoundException | IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @Override
    public Patient getPatientById(Long id) {
        try {
            return dao.findById(id);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Map<Integer, List<Patient>> getPatientByAge() {
        return dao.getPatientByAge();
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        try {
           return dao.sortPatientsByAge(ascOrDesc);
        } catch (NotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
