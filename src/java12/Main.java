package java12;
import java12.dao.impl.DepartmentDaoImpl;
import java12.dao.impl.DoctorDaoImpl;
import java12.dao.impl.HospitalDaoImpl;
import java12.dao.impl.PatientDaoImpl;
import java12.database.DataBase;
import java12.enums.Gender;
import java12.models.Department;
import java12.models.Doctor;
import java12.models.Hospital;
import java12.models.Patient;
import java12.service.HospitalService;
import java12.service.impl.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        HospitalService hospital = new HospitalServiceImpl(new HospitalDaoImpl(dataBase));
        DepartmentServiceImpl department = new DepartmentServiceImpl(new DepartmentDaoImpl(dataBase));
        DoctorServiceImpl doctor = new DoctorServiceImpl(new DoctorDaoImpl(dataBase));
        PatientServiceImpl patient = new PatientServiceImpl(new PatientDaoImpl(dataBase));
        Scanner scanner = new Scanner(System.in);
        OuterLoop:
        while (true) {
            try {
                menu();
                switch (scanner.nextInt()) {
                    case 0 -> {break OuterLoop;}
                    case 1 -> System.out.println(hospital.addHospital(new Hospital("Городская больница", "Ахунбаева 166")));
                    case 2 -> System.out.println(hospital.findHospitalById(1L));
                    case 3 -> System.out.println(hospital.getAllHospital());
                    case 4 -> System.out.println(hospital.getAllPatientFromHospital(1L));
                    case 5 -> System.out.println(hospital.deleteHospitalById(1L));
                    case 6 -> System.out.println(hospital.getAllHospitalByAddress("Ахунбаева 166"));
                    case 7 -> System.out.println(department.add(1L, new Department("Хирургия")));
                    case 8 -> System.out.println(department.removeById(2L));
                    case 9 -> System.out.println(department.updateById(1L, new Department("Невропотолог")));
                    case 10 -> System.out.println(department.getAllDepartmentByHospital(1L));
                    case 11 -> System.out.println(department.findDepartmentByName("Хирургия"));
                    case 12 -> System.out.println(doctor.add(1L, new Doctor("Нурлан", "Абыбибиллаев", Gender.MALE, 10)));
                    case 13 -> System.out.println(doctor.removeById(1L));
                    case 14 -> System.out.println(doctor.updateById(1L, new Doctor("Айтурган", "Максатбек кызы", Gender.FEMALE, 15)));
                    case 15 -> System.out.println(doctor.findDoctorById(1L));
                    case 16 -> System.out.println(doctor.assignDoctorToDepartment(1L, ids()));
                    case 17 -> System.out.println(doctor.getAllDoctorsByHospitalId(1L));
                    case 18 -> System.out.println(doctor.getAllDoctorsByDepartmentId(1L));
                    case 19 -> System.out.println(patient.add(1L, new Patient("Мырзайым", "Келдибекова", 20, Gender.FEMALE)));
                    case 20 -> System.out.println(patient.removeById(1L));
                    case 21 -> System.out.println(patient.updateById(1L, new Patient("Мирлан", "Арстанбеков", 21, Gender.MALE)));
                    case 22 -> System.out.println(patient.addPatientsToHospital(1L, patients()));
                    case 23 -> System.out.println(patient.getPatientById(7L));
                    case 24 -> System.out.println(patient.getPatientByAge());
                    case 25 -> System.out.println(patient.sortPatientsByAge("asc"));
                    default -> System.out.println("Enter correct choice!!!");
                }
            } catch (InputMismatchException e) {
                System.err.println("Enter valid Integer");
                scanner.next();
            }
        }
    }
    private static void menu() {
        System.out.println("""
                0 ->  Exit                                         13 -> Remove Doctor by ID
                1 ->  Add Hospital                                 14 -> Update Doctor by ID
                2 ->  Find Hospital by ID                          15 -> Find Doctor by ID
                3 ->  Get all Hospitals                            16 -> Assign Doctors to Department
                4 ->  Get all Patient from Hospital                17 -> Get all Doctors by Hospital ID
                5 ->  Delete Hospital by ID                        18 -> Get all Doctors by Department ID
                6 ->  Get all Hospitals by address                 19 -> Add Patient to Hospital by ID
                7 ->  Add Department to Hospital by ID             20 -> Remove Patient by ID
                8 ->  Remove Department by ID                      21 -> Update Patient by ID
                9 ->  Update Department by ID                      22 -> Add Patients to Hospital by ID
                10 -> Get all Department by Hospital ID            23 -> Get Patient by ID
                11 -> Find Department by name                      24 -> Get Patient by age
                12 -> Add Doctor to Hospital by ID                 25 -> Sort Patient by age
                """);
        System.out.print("enter command: ");
    }
    private static List<Patient> patients(){
        return new ArrayList<>(Arrays.asList(
                new Patient("Жигит", "Турумбеков", 24, Gender.MALE),
                new Patient("Гулумкан", "Садирова", 19, Gender.FEMALE),
                new Patient("Айзат", "Дуйшеева", 18, Gender.FEMALE),
                new Patient("Датка", "Маматжанова", 21, Gender.FEMALE)
        ));
    }
    private static List<Long> ids(){
        return new ArrayList<>(List.of(1L));
    }

    
}
