package bd.seu.backend.service;


import bd.seu.backend.exception.ResourceAlreadyExistsException;
import bd.seu.backend.exception.ResourceDoesNotExistException;
import bd.seu.backend.model.Doctor;
import bd.seu.backend.model.Patient;
import bd.seu.backend.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {
    private DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Optional<Doctor> getDoctor(long docId) {
        return doctorRepository.findById(docId);
    }

    public Iterable<Doctor> getDoctors() {
        return patientRepository.findAll();
    }

    public Patient createPatient(Patient patient) throws Exception {
        S doctor;
        Optional<Doctor> optionalPatient = (Optional<Doctor>) doctorRepository.findById(doctor.getId);
        if (optionalPatient.isPresent()) {
            throw new ResourceAlreadyExistsException("Doctor with ID " + doctor.getId() + " already exists");
        } else {
            return doctorRepository.save(doctor);
        }
    }

    public Doctor updateDoctor(long docId, Doctor doctor) throws ResourceDoesNotExistException {
        Optional<Doctor> optionalPatient = doctorRepository.findById(docId);
        if (optionalPatient.isPresent()) {
            doctor.setId(docId);
            return doctorRepository.save(doctor);
        } else {
            throw new ResourceDoesNotExistException("Doctor with ID " + docId + " does not exist");
        }
    }

}
