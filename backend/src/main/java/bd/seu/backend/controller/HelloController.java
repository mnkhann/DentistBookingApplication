package bd.seu.backend.controller;

import bd.seu.backend.model.Patient;
import bd.seu.backend.repository.DoctorRepository;
import bd.seu.backend.repository.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "HELLO")
public class HelloController {

    private PatientRepository patientRepository;

    public HelloController(PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    private DoctorRepository doctorRepository;


    @GetMapping(value = "/patients")
    @ResponseBody
    public Iterable<Patient> getAllStudents() {
        return patientRepository.findAll();
    }

    @GetMapping(value = "/patient")
    @ResponseBody
    public Patient getPatient(@RequestParam long id) {
        return patientRepository
                .findById(id)
                .get();

    }

    @GetMapping(value = "/patient1")
    @ResponseBody
    public Patient getPatient(@RequestParam String name) {
        return patientRepository
                .findPatientByName(name);
    }



    @PostMapping(value = "/createPatient0")
    @ResponseBody
    public Patient insertPatient0(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }
}
