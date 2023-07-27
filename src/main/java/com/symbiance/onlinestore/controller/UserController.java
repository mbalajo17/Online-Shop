package com.symbiance.onlinestore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.symbiance.onlinestore.model.LoginUser;
import com.symbiance.onlinestore.model.Role;
import com.symbiance.onlinestore.model.User;
import com.symbiance.onlinestore.repository.Rolerepository;
import com.symbiance.onlinestore.repository.Userrepository;
import com.symbiance.onlinestore.response.JwtResponse;
import com.symbiance.onlinestore.security.JwtUtils;
import com.symbiance.onlinestore.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private Userrepository userRepository;
    @Autowired
    private Rolerepository roleRepository;
    @Autowired
    JwtUtils jwtTokenUtils;
    @Autowired
    PasswordEncoder encoder;

    @PostMapping (path = "/login")//, consumes = "multipart/form-data"

    public ResponseEntity<?> loginUser(@RequestParam ("loginUser") String data) throws IOException, NullPointerException{
        LoginUser user = new ObjectMapper().readValue(data, LoginUser.class);
        if(!userRepository.existsByEmail(user.getEmail())){
            return ResponseEntity.badRequest().body("User with this email is not exist");
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(new JwtResponse(jwt,userDetails.getId(), userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping(path = "/register")//, consumes = "multipart/form-data"
    public ResponseEntity<?> registerUser(@RequestParam("registerUser") String registerUser) throws IOException {
        User userData = new ObjectMapper().readValue(registerUser, User.class);
       Role role=roleRepository.findByName("ROLE_USER");

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        User user = new User(userData.getId(), userData.getUserName(), userData.getEmail(), encoder.encode(userData.getPassword()), userData.getRole(),roles);

        if(userRepository.existsByUserName(user.getUserName())){
            return ResponseEntity.badRequest().body("Error: Name is already taken");
        }
        if(userRepository.existsByEmail(user.getEmail())){
            return ResponseEntity.badRequest().body("Error: Email is already taken");
        }
        userRepository.save(user);

        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping("/re/{id}")
    public User getUserById(@PathVariable  Long id){
        return userRepository.findUserById(id);
    }
}
