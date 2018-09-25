package bd.seu.frontend.ui;


import bd.seu.frontend.model.Doctor;
import com.vaadin.annotations.Title;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;


@SpringUI
@Title("Smile Dental Care")
public class HomeUI extends UI {

    private Doctor doctorService;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        VerticalLayout verticalLayout = new VerticalLayout();


        FormLayout formLayout0 = new FormLayout();

        Label appName = new Label("Smile Dental Clinic");
        appName.addStyleName(ValoTheme.LABEL_H1);
        HorizontalLayout h0 = new HorizontalLayout();
        h0.addComponent(appName);
        h0.setDefaultComponentAlignment(Alignment.TOP_CENTER);


        TextField usernameTextfield = new TextField("Username");
        usernameTextfield.setRequiredIndicatorVisible(true);
        usernameTextfield.setIcon(VaadinIcons.USER_STAR);
        usernameTextfield.setMaxLength(20);


        PasswordField passwordField = new PasswordField("Password");
        passwordField.setRequiredIndicatorVisible(true);
        passwordField.setIcon(VaadinIcons.PASSWORD);

        Button loginButton = new Button("Login");
        loginButton.addStyleName(ValoTheme.BUTTON_PRIMARY);


        formLayout0.addComponents(usernameTextfield, passwordField, loginButton);

        VerticalLayout v3 = new VerticalLayout();
        v3.addComponent(formLayout0);


        FormLayout formLayout = new FormLayout();

        TextField tfName = new TextField("Name");
        tfName.setRequiredIndicatorVisible(true);
        tfName.setIcon(VaadinIcons.USER);
        tfName.setMaxLength(20);

        TextField tfAge = new TextField("Age");
        tfAge.setRequiredIndicatorVisible(true);
        tfAge.setIcon(VaadinIcons.BELL);
        tfAge.setMaxLength(2);

        TextField tfPhone = new TextField("Phone");
        tfPhone.setRequiredIndicatorVisible(true);
        tfPhone.setIcon(VaadinIcons.PHONE);
        tfPhone.setMaxLength(11);

        PasswordField signupPassword = new PasswordField("Password");
        signupPassword.setRequiredIndicatorVisible(true);
        signupPassword.setIcon(VaadinIcons.PASSWORD);

        PasswordField retypePassword = new PasswordField("Retype Password");
        retypePassword.setRequiredIndicatorVisible(true);
        retypePassword.setIcon(VaadinIcons.PASSWORD);

        Button signUp = new Button("Sign Up");
        signUp.addStyleName(ValoTheme.BUTTON_PRIMARY);


        formLayout.addComponents(tfName, tfAge, tfPhone, signupPassword, retypePassword, signUp);

        VerticalLayout v2 = new VerticalLayout();
        v2.addComponent(formLayout);

        VerticalLayout v1 = new VerticalLayout();
        v1.addComponents(h0, v3, v2);
        v1.addStyleName(ValoTheme.THEME_NAME);

        verticalLayout.addComponent(v1);

        setContent(verticalLayout);


    }
}
