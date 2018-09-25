package bd.seu.frontend.service;

import bd.seu.frontend.model.Doctor;
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
public class DoctorService {
    private ObjectMapper objectMapper;

    public DoctorService(ObjectMapper objectMapper) {
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


    public List<Doctor> getDoctor(int docId) {
        String username = "test";
        String password = "rest";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Doctor> doctorResponseEntity = restTemplate.exchange(
                "http://localhost:8084/doctor/" + docId,
                HttpMethod.GET,
                new HttpEntity<>(createHeaders(username, password)),
                Doctor.class);
        List<Doctor> doctorList = new ArrayList<>();
        if (doctorResponseEntity.getStatusCode() == HttpStatus.OK)
            doctorList.add(doctorResponseEntity.getBody());
        return doctorList;
    }


    public List<Doctor> getAllDoctors() {
        String message = "";
        Doctor[] doctors = null;
        try {
            URL url = new URL("http://locahost:8084/doctor/all");
            doctors = objectMapper.readValue(url, Doctor[].class);
            System.out.println("toString from object: " + Arrays.toString(doctors));

        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Doctor> doctorList = new ArrayList<>();
        doctorList = new ArrayList<>();
        doctorList.add(new Doctor());
        return doctorList;
    }

    public Doctor saveDoctor(Doctor doctor) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Doctor> doctorHttpEntity = new HttpEntity<>(doctor);
        ResponseEntity<Doctor> doctorResponseEntity = restTemplate.exchange(
                "http://locahost:8084/doctor",
                HttpMethod.POST,
                doctorHttpEntity,
                Doctor.class);
        if (doctorResponseEntity.getStatusCode() == HttpStatus.CREATED)
            return doctorResponseEntity.getBody();
        else throw new Exception(doctorResponseEntity.toString());
    }


}


