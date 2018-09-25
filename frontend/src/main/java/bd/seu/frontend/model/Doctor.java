package bd.seu.frontend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Doctor implements Serializable {

    private int docId;
    private String docName;
    private String docDetails;
    private int docPhone;

}
