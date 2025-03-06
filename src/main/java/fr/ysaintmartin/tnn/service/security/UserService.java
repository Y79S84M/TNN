package fr.ysaintmartin.tnn.service.security;

import fr.ysaintmartin.tnn.entity.security.UserCredentials;
import fr.ysaintmartin.tnn.enumeration.security.Role;
import fr.ysaintmartin.tnn.model.security.UserInformation;
import fr.ysaintmartin.tnn.model.security.UserRegistration;
import fr.ysaintmartin.tnn.repository.security.UserRepository;
import fr.ysaintmartin.tnn.utility.CustomDateTimeFormatter;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserRegistration registerNewUser(UserRegistration userRegistration) {
        if (userRepository.existsByUsername(userRegistration.username()))
            throw new EntityExistsException("A user with the same username already exists");

        UserCredentials user = UserCredentials.builder()
                .password(passwordEncoder.encode(userRegistration.password()))
                .username(userRegistration.username())
                .creationDate(LocalDateTime.now())
                .role(Role.USER)
                .build();
        userRepository.save(user);

        return userRegistration;
    }

    public UserInformation getUserInformation(long userId) {
        return userRepository.findById(userId)
                .map(userCredentials ->
                        new UserInformation(userCredentials.getEmail(),
                                userCredentials.getUsername(),
                                CustomDateTimeFormatter.formatDateTime(userCredentials.getCreationDate()),
                                CustomDateTimeFormatter.formatDateTime(userCredentials.getUpdateDate()),
                                CustomDateTimeFormatter.formatDateTime(userCredentials.getLastLoginDate())))
                .orElseThrow(() -> new EntityNotFoundException("Could not find the user with id: " + userId));
    }
}
