package bd.seu.backend.service;


import bd.seu.backend.repository.PatientRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")

public class PatientServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    @Before
    public void deletePatients() {
        patientRepository.deleteAll();
    }
}
