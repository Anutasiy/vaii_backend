package com.mati.vaii.api;

import static org.springframework.http.ResponseEntity.ok;

import java.time.Instant;
import java.util.*;

import javax.annotation.PostConstruct;

import com.mati.vaii.dto.AuthDTO;
import com.mati.vaii.dto.UserDTO;
import com.mati.vaii.model.Roles;
import com.mati.vaii.model.Users;
import com.mati.vaii.repository.UserRepository;
import com.mati.vaii.security.config.JwtTokenProvider;
import com.mati.vaii.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository users;

    @Autowired
    private UserService userService;

//    @Autowired
//    private ApplicationEventPublisher eventPublisher;
//
//    @Autowired
//    private PasswordEncoder bCryptPasswordEncoder;

    private String url = "http://matifamily.com";


    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthDTO data) {
        try {

            String username = data.getEmail().toLowerCase();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            Users user = this.users.findByEmail(username);
//            Long l= new Long(2);
//            Users user = this.users.findById(l).orElse(null);
            String roles = user.getRole();
            String token = jwtTokenProvider.createToken(username, roles.toString());
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            model.put("role", roles);
            model.put("expirationInSeconds", jwtTokenProvider.getValidityInMilliseconds());
            model.put("expirationTime",
                    Instant.now().toEpochMilli() + jwtTokenProvider.getValidityInMilliseconds());
            return ok(model);


        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email/password supplied");
        }
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserDTO user) {
        user.email = user.email.toLowerCase();
        Users userExists = userService.findUserByEmail(user.email);
        if (userExists != null) {
            throw new BadCredentialsException("User with username: " + user.email + " already exists");

        }

        user.enabled = false;
        user.confirmationToken = (UUID.randomUUID().toString());
        userService.saveUser(user);


        Map<Object, Object> model = new HashMap<>();
        model.put("message", "User registered successfully");
        return ok(model);


    }






//    @PostConstruct
//    public void UserController() {
//        User userExists = userService.findUserByEmail("email");
//        if (userExists == null) {
//            UserDTO user = new UserDTO();
//            user.enabled = true;
//            user.birthNumber = "1111111";
//            user.email = "email";
//            user.password = "heslo";
//            ArrayList<String> roles = new ArrayList<String>();
//            roles.add("ADMIN");
//            user.listOfRoles = roles;
//
//            userService.saveUser(user);
//        }
//
//    }

}
