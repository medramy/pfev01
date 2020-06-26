package com.esprit.pfe.pfev01.Controller;

import com.esprit.pfe.pfev01.Exception.ResourceNotFoundException;
import com.esprit.pfe.pfev01.Model.ERole;
import com.esprit.pfe.pfev01.Model.Role;
import com.esprit.pfe.pfev01.Model.User;
import com.esprit.pfe.pfev01.Repository.RoleRepository;
import com.esprit.pfe.pfev01.Repository.UserRepository;
import com.esprit.pfe.pfev01.Service.MyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    public JavaMailSender emailSender;

    @PostMapping("/user")
    public User createUser(@Valid @RequestBody User user) {

        return userRepository.save(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        List<User> users= userRepository.findAll();
        Role r=roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        if (!users.isEmpty()){
            users.removeIf(user -> user.getRoles().contains(r));
        }

        return users;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long UserId)
            throws ResourceNotFoundException {
        User user = userRepository.findById(UserId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + UserId));
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/Activate/{id}")
    public User Activate(@PathVariable(value = "id") Long UserId)
            throws ResourceNotFoundException {
        User user = userRepository.findById(UserId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + UserId));
        user.setActive(1);
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(user.getEmail());
        message.setSubject("Account activation");
        message.setText("Bonjour "+user.getUsername()+", votre compte est activé, vous pouvez maintenant se connecter");

        // Send Message!
        this.emailSender.send(message);
        return userRepository.save(user);
    }

    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long UserId)
            throws ResourceNotFoundException {
        User user = userRepository.findById(UserId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + UserId));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long UserId,
                                           @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(UserId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + UserId));

        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setIdentifiant(userDetails.getIdentifiant());
        user.setRaison_sociale(userDetails.getRaison_sociale());
        user.setEmail(userDetails.getEmail());
        user.setNumlic(userDetails.getNumlic());
        user.setNumphone(userDetails.getNumphone());
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }
}
