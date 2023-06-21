package com.project.schoolService.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "laptop_part")
public class LaptopPart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPart;
    private String name;
    private String description;
    private Double price;
    private Double stock;
    @OneToMany(mappedBy = "laptopPart")
    private List<Ticket> tickets;

    public LaptopPart() {
    }

    public LaptopPart(Long idPart, String name, String description, Double price, Double stock, List<Ticket> tickets) {
        this.idPart = idPart;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.tickets = tickets;
    }

    public Long getIdPart() {
        return idPart;
    }

    public void setIdPart(Long idPart) {
        this.idPart = idPart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "LaptopPart{" +
                "idPart=" + idPart +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", tickets=" + tickets +
                '}';
    }
}
