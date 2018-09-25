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
public class Doctor {

    @Id
    @Length(max = 3, min = 1)
    private long docId;
    private String docName;
    private String docDetails;

    public Doctor(@Length(max = 3, min = 1) long docId, String docName, String docDetails) {
        this.docId = docId;
        this.docName = docName;
        this.docDetails = docDetails;
    }
}
