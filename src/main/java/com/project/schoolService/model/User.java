package com.project.schoolService.model;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //
    @Column(unique = true)
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;

    public User() {
    }

    public User(Long id, String username, String password, UserRole userRole, List<Ticket> tickets) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.tickets = tickets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                ", tickets=" + tickets +
                '}';
    }
}
