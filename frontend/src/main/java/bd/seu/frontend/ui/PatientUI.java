package bd.seu.frontend.ui;

import bd.seu.frontend.model.Patient;
import com.vaadin.annotations.Title;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.data.Binder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Optional;

import static bd.seu.frontend.model.Patient.getpatientId;

@SpringComponent
@Title("Take Appointment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PatientUI extends VerticalLayout {

    private Binder<Patient> patientBinder;

    private boolean isNumber(String string) {
        for (int i = 0; i < string.length(); i++)
            if (!Character.isDigit(string.charAt(i)))
                return false;
        return true;
    }

    public Optional<Patient> getPatient() {
        Patient patient = new Patient();
        if (patientBinder.writeBeanIfValid(patient))
            return Optional.of(patient);
        else return Optional.empty();
    }

    public void setPatient(Patient patient) {
        patientBinder.readBean(patient);
    }

    public PatientUI() {

        super();
        this.setCaption("Welcome");
        TextField patientId = new TextField("ID");
        patientId.setRequiredIndicatorVisible(true);
        patientId.setIcon(VaadinIcons.DIAMOND);

        TextField patientName = new TextField("Name");
        patientName.setRequiredIndicatorVisible(true);
        patientName.setIcon(VaadinIcons.USER);

        TextField patientAge = new TextField("Age");
        patientAge.setRequiredIndicatorVisible(true);
        patientAge.setIcon(VaadinIcons.CHECK_CIRCLE);

        TextField patientPhone = new TextField("Phone");
        patientPhone.setRequiredIndicatorVisible(true);
        patientPhone.setIcon(VaadinIcons.PHONE);


        patientBinder = new Binder<>();
        patientBinder
                .forField(patientName)
                .asRequired("Names cannot be empty")
                .withValidator(p -> p.length() >= 3, "Names should be at least 3 letters long")
                .bind(Patient::getName, Patient::setName);


        Binder.Binding<Patient, String> bind1 = patientBinder
                .forField(patientId)
                .asRequired()
                .withValidator(p -> isNumber(p), "Patirnt ID must be a number")
                .bind(Patient -> {
                            return getpatientId();
                        },
                        (patient, p) -> patient.setpatientId(Long.parseLong(p)));

        Binder.Binding<Patient, String> bind = patientBinder
                .forField(patientPhone)
                .asRequired()
                .withValidator(p -> isNumber(p), "Patient Phone must be a number")
                .withValidator(p -> p.length() == 11, "Should be 11 digits")
                .bind(patient -> {
                    final String s = Patient::getpatientPhone;
                    return s;
                }, Patient::setpatientPhone);





    }
}
