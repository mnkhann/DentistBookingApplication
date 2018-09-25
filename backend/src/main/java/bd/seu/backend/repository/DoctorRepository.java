package bd.seu.backend.repository;

import bd.seu.backend.model.Doctor;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    public Doctor findDoctorByName(String docName);
}
