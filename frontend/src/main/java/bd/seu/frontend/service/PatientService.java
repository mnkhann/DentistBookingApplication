package bd.seu.frontend.service;

import bd.seu.frontend.model.Doctor;
import bd.seu.frontend.model.Patient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class PatientService {
    private ObjectMapper objectMapper;

    public PatientService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private HttpHeaders createHeaders(String username, String password){
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
    }


    public List<Patient> getPatient(int patientId) {
        String username = "test";
        String password = "rest";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Patient> patientResponseEntity = restTemplate.exchange(
                "http://localhost:8084/patient/" + patientId,
                HttpMethod.GET,
                new HttpEntity<>(createHeaders(username, password)),
                Patient.class);
        List<Patient> patientList = new ArrayList<>();
        if (patientResponseEntity.getStatusCode() == HttpStatus.OK)
            patientList.add(patientResponseEntity.getBody());
        return patientList;
    }


    public List<Patient> getAllPatients() {
        String message = "";
        Patient[] patients = null;
        try {
            URL url = new URL("http://locahost:8084/patient/all");
            patients = objectMapper.readValue(url, Patient[].class);
            System.out.println("toString from object: " + Arrays.toString(patients));

        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Patient> patientList = new ArrayList<>();
        patientList = new ArrayList<>();
        patientList.add(new Patient());
        return patientList;
    }

    public Patient savePatient(Patient patient) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Patient> patientHttpEntity = new HttpEntity<>(patient);
        ResponseEntity<Patient> patientResponseEntity = restTemplate.exchange(
                "http://locahost:8084/patient",
                HttpMethod.POST,
                patientHttpEntity,
                Patient.class);
        if (patientResponseEntity.getStatusCode() == HttpStatus.CREATED)
            return patientResponseEntity.getBody();
        else throw new Exception(patientResponseEntity.toString());
    }


}


