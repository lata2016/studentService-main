package com.project.schoolService.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "laptopPart_id")
    private LaptopPart laptopPart;
    private String description;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Ticket() {
    }

    public Ticket(Long id, LaptopPart laptopPart, String description, TicketStatus status) {
        this.id = id;
        this.laptopPart = laptopPart;
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public LaptopPart getLaptopPart() {
        return laptopPart;
    }

    public void setLaptopPart(LaptopPart laptopPart) {
        this.laptopPart = laptopPart;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", laptopPart=" + laptopPart +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
