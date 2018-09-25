package bd.seu.backend.model;


import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@ToString
@Entity
@NotNull
public class Patient {

    @Id
    @Length(max = 3, min = 1)
    private long patientId;
    private String patientName;
    @Length(min = 1, max = 3)
    private int patientAge;
    @Length(max = 11, min = 9)
    private int patientPhone;

    public Patient(@Length(max = 3, min = 1) long patientId, String patientName, @Length(min = 1, max = 3) int patientAge, @Length(max = 11, min = 9) int patientPhone) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.patientPhone = patientPhone;
    }


}
