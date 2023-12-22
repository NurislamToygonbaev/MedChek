package java12.service.impl;
import java12.dao.HospitalDao;
import java12.models.Hospital;
import java12.models.Patient;
import java12.service.HospitalService;
import java.util.List;
import java.util.Map;

public class HospitalServiceImpl implements HospitalService {

    private final HospitalDao hospitalDao;

    public HospitalServiceImpl(HospitalDao hospitalDao) {
        this.hospitalDao = hospitalDao;
    }

    @Override
    public String addHospital(Hospital hospital) {
        try {
            hospitalDao.add(hospital);
            return "Successfully saved";
        } catch (NotFoundException e){
            return e.getMessage();
        }
    }

    @Override
    public Hospital findHospitalById(Long id) {
        try {
           return hospitalDao.findById(id);
        } catch (NotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Hospital> getAllHospital() {
        return hospitalDao.getAll();
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        try {
           return hospitalDao.getAllPatientFromHospital(id);
        } catch (NotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteHospitalById(Long id) {
        try {
            Boolean delete = hospitalDao.delete(id);
            if (delete.equals(false)) System.out.println(false);
            return "Successfully deleted";
        } catch (NotFoundException e){
            return e.getMessage();
        }
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        try {
            return hospitalDao.getAllHospitalByAddress(address);
        } catch (NotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
