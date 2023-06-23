package com.project.schoolService.service;

import com.project.schoolService.model.User;
import com.project.schoolService.model.UserRequest;
import com.project.schoolService.model.UserResponse;
import com.project.schoolService.model.UserRole;
import com.project.schoolService.repository.TokenRepository;
import com.project.schoolService.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, TokenRepository tokenRepository, AuthenticationService authenticationService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.authenticationService = authenticationService;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse getUser() {
        User user = authenticationService.getUser();
        return UserResponse.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .userRole(user.getUserRole())
                .tickets(user.getTickets())
                .build();
    }
    //regjistrojme ADMIN-in
    public void registerAdmin(UserRequest request) {
        User user = User.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .userRole(UserRole.ADMIN)
                .tickets(request.tickets())
                .build();
        userRepository.save(user);
    }

    @Transactional
    public void delete() {
        try {
            User user = authenticationService.getUser();
            tokenRepository.deleteAllByUserId(user.getId());
            userRepository.delete(user);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't delete user!\n" + e.getMessage());
        }
    }

    /*
    private UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    //regjistrojme nje user si ADMIN
    public User saveAdmin(User user){
        User adminUser = new User();
        adminUser.setId(user.getId());
        adminUser.setUsername(user.getUsername());
        adminUser.setPassword(user.getPassword());
        adminUser.setUserRole(UserRole.ADMIN);
        adminUser.setTickets(user.getTickets());

        return userRepository.save(adminUser);
    }
    public User saveUser(User user){
        User simpleUser = new User();
        simpleUser.setId(user.getId());
        simpleUser.setUsername(user.getUsername());
        simpleUser.setPassword(user.getPassword());
        simpleUser.setUserRole(UserRole.SIMPLE_USER);
        simpleUser.setTickets(user.getTickets());

       return userRepository.save(simpleUser);
    }

     */
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }
    public User save(User user){
        return userRepository.save(user);
    }



}

