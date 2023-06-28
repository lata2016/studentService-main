package com.project.schoolService.service;

import com.project.schoolService.model.LaptopPart;
import com.project.schoolService.model.Ticket;
import com.project.schoolService.model.TicketStatus;
import com.project.schoolService.repository.LaptopPartRepository;
import com.project.schoolService.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private TicketRepository ticketRepository;
    private LaptopPartRepository laptopPartRepository;


    public TicketService(TicketRepository ticketRepository, LaptopPartRepository laptopPartRepository) {
        this.ticketRepository = ticketRepository;
        this.laptopPartRepository = laptopPartRepository;
    }
    //CRUD METHODS
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket createTicket(Long id, String description) {
        Optional<LaptopPart> optionalPart = laptopPartRepository.findById(id);
        if (optionalPart.isEmpty()) {
            throw new IllegalArgumentException("Part of laptop with this id " + id + "do not exist");
        }

        LaptopPart laptopPart = optionalPart.get();
        if (laptopPart.getStock() == 0) {
            throw new IllegalStateException("Laptop part is out of stock");
        }

        laptopPart.setStock(laptopPart.getStock() - 1); // ulim sasine e stock kur krijojme nje new Ticket
        laptopPartRepository.save(laptopPart);

        Ticket ticket = new Ticket();
        ticket.setId(id);
        ticket.setLaptopPart(laptopPart);
        ticket.setDescription(description);
        ticket.setStatus(TicketStatus.OPEN);

        return ticketRepository.save(ticket);
    }
    public Ticket closeTicket(Long id) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        Ticket ticket = optionalTicket.get();
        if (ticket.getStatus() == TicketStatus.CLOSED) {
            throw new IllegalStateException("Ticket is already closed");
        }

        ticket.setStatus(TicketStatus.CLOSED);
        return ticketRepository.save(ticket);
    }
    public Ticket openTicket(Long id) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        Ticket ticket = optionalTicket.get();
        if (ticket.getStatus() == TicketStatus.OPEN) {
            throw new IllegalStateException("Ticket is already closed");
        }

        ticket.setStatus(TicketStatus.OPEN);
        return ticketRepository.save(ticket);
    }


    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ticket ID"));
    }

    public void deleteTicket(Long id){
        ticketRepository.deleteById(id);
    }

    public Ticket updateTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket addTicket(Ticket ticket) {
        ticket.setId(ticket.getId());
        return ticketRepository.save(ticket);
    }

}

