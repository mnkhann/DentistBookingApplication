package bd.seu.backend.repository;

import bd.seu.backend.model.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Long> {
    public Patient findPatientByName(String patientName);
}
