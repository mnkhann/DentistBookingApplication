package bd.seu.backend.controller;

import bd.seu.backend.exception.ResourceDoesNotExistException;
import bd.seu.backend.model.Doctor;
import bd.seu.backend.service.DoctorService;
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
@RequestMapping(value = "doctor")
@AllArgsConstructor

public class DoctorController {
    private DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping(value = "all")
    @ResponseBody

    public List<Doctor> getAllDoctors() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Doctor> doctorList = new ArrayList<>();
        doctorService.getDoctors().forEach(doctorList::add);
        return doctorList;
    }

    @GetMapping(value = "{id}")
    @ResponseBody
    public ResponseEntity<Doctor> getDoctor(@PathVariable long id) throws ResourceDoesNotExistException {
        System.out.println("GET request for ID " + id);
        Optional<Doctor> optionalDoctor = doctorService.getDoctor(id);

        if (optionalDoctor.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(optionalDoctor.get());
        else throw new ResourceDoesNotExistException("Doctor not found");
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        try {
            Doctor createDoctor = doctorService.createDoctor(doctor);
            ResponseEntity<Doctor> doctorResponseEntity = ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(createDoctor);
            return  doctorResponseEntity;
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping(value = "{docId}")
    @ResponseBody
    public Doctor updateDoctor(@PathVariable long docId, @RequestBody Doctor doctor) throws ResourceDoesNotExistException {
        return doctorService.updateDoctor(docId, doctor);
    }



}

