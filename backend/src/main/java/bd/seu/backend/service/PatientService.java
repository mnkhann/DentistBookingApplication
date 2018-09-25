package bd.seu.backend.service;


import bd.seu.backend.exception.ResourceAlreadyExistsException;
import bd.seu.backend.exception.ResourceDoesNotExistException;
import bd.seu.backend.model.Patient;
import bd.seu.backend.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Optional<Patient> getPatient(long patientId) {
        return patientRepository.findById(patientId);
    }

    public Iterable<Patient> getPatients() {
        return patientRepository.findAll();
    }

    public Patient createPatient(Patient patient) throws Exception, ResourceAlreadyExistsException {
        Optional<Patient> optionalPatient = patientRepository.findById(patient.getId);
        if (optionalPatient.isPresent()) {
            throw new ResourceAlreadyExistsException("Patient with ID " + patient + " already exists");
        } else {
            return patientRepository.save(patient);
        }
    }

    public Patient updatePatient(long patientId, Patient patient) throws ResourceDoesNotExistException {
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if (optionalPatient.isPresent()) {
            patient.setId(patientId);
            return patientRepository.save(patient);
        } else {
            throw new ResourceDoesNotExistException("Patient with ID " + patientId + " does not exist");
        }
    }

}
