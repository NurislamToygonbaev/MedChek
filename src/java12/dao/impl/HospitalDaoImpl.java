package java12.dao.impl;

import java12.dao.HospitalDao;
import java12.database.DataBase;
import java12.models.Hospital;
import java12.models.Patient;
import java12.service.impl.NotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HospitalDaoImpl implements HospitalDao {

    private final DataBase dataBase;

    public HospitalDaoImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public Hospital findById(Long hospitalId) {
        for (Hospital hospital : dataBase.getAll()) {
            if (hospital.getId().equals(hospitalId)){
                return hospital;
            }
        }
        throw new NotFoundException("Hospital with "+hospitalId+" not found");
    }

    @Override
    public Boolean add(Hospital hospital) {
        for (Hospital hospital1 : dataBase.getAll()) {
            if (hospital1.getId().equals(hospital.getId())){
                throw new NotFoundException("error");
            }
        }
        return dataBase.save(hospital);
    }

    @Override
    public Boolean delete(Long id) {
        for (Hospital hospital : dataBase.getAll()) {
            if (hospital.getId().equals(id)){
               return dataBase.remove(hospital);
            }
        }
        throw new NotFoundException("Hospital with "+ id + " not found");
    }

    @Override
    public List<Hospital> getAll() {
        return dataBase.getAll();
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        for (Hospital hospital : dataBase.getAll()) {
            if (hospital.getId().equals(id)){
                return hospital.getPatients();
            }
        }
        throw new NotFoundException("Hospital with "+ id + " not found");
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        Map<String, Hospital> hospitalMap = new HashMap<>();
        for (Hospital hospital : dataBase.getAll()) {
            if (hospital.getAddress().equalsIgnoreCase(address)){
                hospitalMap.put(address, hospital);
                return hospitalMap;
            }
        }
        throw new NotFoundException("Hospital with "+address+" now found");
    }
}
