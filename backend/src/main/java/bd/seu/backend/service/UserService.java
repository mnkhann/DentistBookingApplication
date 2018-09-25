package bd.seu.backend.service;


import bd.seu.backend.model.User;
import bd.seu.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public User createUser(User user) {
        // make sure that you're not creating duplicates
        return userRepository.save(user);
    }

    public User getUser(String username, String password) {
        // needs fixing
        return userRepository.findById(username).get();
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }
}