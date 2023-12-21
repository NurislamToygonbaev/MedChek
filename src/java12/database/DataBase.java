package java12.database;

import java12.models.Hospital;

import java.util.LinkedList;
import java.util.List;

public class DataBase {
    private List<Hospital> hospitals = new LinkedList<>();

    public Boolean save(Hospital hospital){
        return hospitals.add(hospital);
    }

    public Boolean remove(Hospital hospital){
        return hospitals.remove(hospital);
    }

    public List<Hospital> getAll(){
        return hospitals;
    }
}
