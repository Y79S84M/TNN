package fr.ysaintmartin.tnn.controller.security;

import fr.ysaintmartin.tnn.model.security.UserInformation;
import fr.ysaintmartin.tnn.model.security.UserRegistration;
import fr.ysaintmartin.tnn.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegistration> registerNewUser(@RequestBody UserRegistration user) {
        return ResponseEntity.ok(userService.registerNewUser(user));
    }

    @GetMapping("/{id}/information")
    public ResponseEntity<UserInformation> getUserInformation(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUserInformation(id));
    }
}
