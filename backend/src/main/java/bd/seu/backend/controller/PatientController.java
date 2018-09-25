package bd.seu.backend.controller;



import bd.seu.backend.exception.ResourceDoesNotExistException;
import bd.seu.backend.model.Patient;
import bd.seu.backend.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "patient")
@AllArgsConstructor

public class PatientController {
    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(value = "all")
    @ResponseBody

    public List<Patient> getAllPatients() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Patient> patientList = new ArrayList<>();
        patientService.getPatients().forEach(patientList::add);
        return patientList;
    }

    @GetMapping(value = "{id}")
    @ResponseBody
    public ResponseEntity<Patient> getPatient(@PathVariable long id) throws ResourceDoesNotExistException {
        System.out.println("GET request for ID " + id);
        Optional<Patient> optionalPatient = patientService.getPatient(id);

        if (optionalPatient.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(optionalPatient.get());
        else throw new ResourceDoesNotExistException("Patient not found");
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        try {
            Patient createPatient = patientService.createPatient(patient);
            ResponseEntity<Patient> patientResponseEntity = ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(createPatient);
            return  patientResponseEntity;
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping(value = "{patientId}")
    @ResponseBody
    public Patient updatePatient(@PathVariable long patientId, @RequestBody Patient patient) throws ResourceDoesNotExistException {
        return patientService.updatePatient(patientId, patient);
    }

    @DeleteMapping(value = "{patientId}")
    @ResponseBody
    public  Patient deletePatient(@PathVariable long patientId, @ResponseBody Patient patient) {
        return patient.deletePatient(patientId, patient);
    }

}
