package bd.seu.backend.model;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")

public class PatientTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testAllArgsConstructor() {
        Patient patient = new Patient(24, "Salim", 60);
        Assert.assertEquals(24, patient.getpatientId());
        Assert.assertEquals("Salim", patient.getpatientName());
        Assert.assertEquals(60, patient.getpatientAge());
    }

    @Test
    public void testIdRangeUnderflow() {
        Patient patient = new Patient(16, "Jafar", 8 );
        Assert.assertEquals(16, patient.getId());

        patient = new Patient(12, "Jafar", 8);
    }

    @Test
    public void testIdRangeOverflow() {
        Patient patient = new Patient(12, "Jafar", 8);
        Assert.assertEquals(12, patient.getId());

        patient = new Patient(12345, "Jafar", 8);
    }

}
