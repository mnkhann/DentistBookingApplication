package bd.seu.frontend.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode(of = {"patientId"})

public class Patient {
    private int patientId;
    private String patientName;
    private int patietnAge;
    private int patientPhone;

    public static void getpatientId() {
    }

    public void getName(Patient patient) {
    }

    public  void setName(Patient patient, String s) {
    }

    public void setpatientId(long parseLong) {
    }

    public static void setpatientPhone(Patient patient, String s) {
    }
}
