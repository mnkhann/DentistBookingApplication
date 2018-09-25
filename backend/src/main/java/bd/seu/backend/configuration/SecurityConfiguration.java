package bd.seu.backend.configuration;

import bd.seu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) {
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            UserService userService;

            builder
                    .inMemoryAuthentication()
                    .passwordEncoder(encoder)
                    .withUser("test")
                    .password(encoder.encode("rest"))
                    .roles("admin");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}