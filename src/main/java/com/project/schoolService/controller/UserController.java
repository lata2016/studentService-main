package com.project.schoolService.controller;

import com.project.schoolService.model.UserRequest;
import com.project.schoolService.model.UserResponse;
import com.project.schoolService.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete() {
        userService.delete();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody UserRequest user) {
        userService.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/info")
    public ResponseEntity<UserResponse> getUser() {
        return ResponseEntity.ok(userService.getUser());
    }
}
    /*
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtil.generateToken((User) authentication);
            return ResponseEntity.ok(token);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        // Get authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Check if user has permission to access the requested user data
        User requestedUser = userService.findByUsername(username);
        if (requestedUser == null) {
            return ResponseEntity.notFound().build();
        }

        if (authentication.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ADMIN"))
                || username.equals(requestedUser.getUsername())) {
            return ResponseEntity.ok(requestedUser);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

}

     */
    /*

    private UserService userService;

    public UserController ( UserService userService){
        this.userService = userService;
    }
     @GetMapping
     public ResponseEntity<List<User>> getAllUsers() {
            List<User> users = userService.getAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
     }

     @PostMapping("/admin")
     public ResponseEntity<User> createAdminUser(@RequestBody User user) {
            User createUser = userService.saveAdmin(user);
            return new ResponseEntity<>(createUser, HttpStatus.CREATED);
     }

     @PostMapping("/user")
     public ResponseEntity<User> createUser(@RequestBody User user) {
            User createUser = userService.saveUser(user);
            return new ResponseEntity<>(createUser, HttpStatus.CREATED);
     }

     @GetMapping("/{id}")
        public ResponseEntity<User> getUserById(@PathVariable Long id) {
            Optional<User> user = userService.findById(id);
            if (user.isPresent()) {
                return new ResponseEntity<>(user.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
            userService.deleteUserById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }


     */