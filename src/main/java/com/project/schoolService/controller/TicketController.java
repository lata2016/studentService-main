package com.project.schoolService.controller;

import com.project.schoolService.model.Ticket;
import com.project.schoolService.service.LaptopPartService;
import com.project.schoolService.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    private TicketService ticketService;
    private LaptopPartService laptopPartService;

    public TicketController(TicketService ticketService,LaptopPartService laptopPartService){
        this.ticketService = ticketService;
        this.laptopPartService =laptopPartService;
    }
    @GetMapping("/getAllTicket")
    @PreAuthorize("hasAuthority('ADMIN','SIMPLE_USER')")
    public ResponseEntity<List<Ticket>> getAllTicket(){
        List<Ticket> tickets = ticketService.getAllTickets();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }
    @GetMapping("/findTicket/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Ticket> findTicketById(@PathVariable("id") Long id){
        Ticket tickets = ticketService.getTicketById(id);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }
    @PostMapping("/createTicket")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket){
        Ticket newTicket = ticketService.createTicket(ticket.getId(), ticket.getDescription());
        return new ResponseEntity<>(newTicket,HttpStatus.CREATED);
    }
    @PostMapping("/updateTicket/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Ticket> updateTicket(@RequestBody Ticket ticket){
        Ticket updateTicket = ticketService.updateTicket(ticket);
        return new ResponseEntity<>(updateTicket,HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Ticket> addEmployees(@RequestBody Ticket ticket){
        Ticket newTicket = ticketService.addTicket(ticket);
        return new ResponseEntity<>(newTicket,HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteTicket(@PathVariable("id") Long id){
        ticketService.deleteTicket(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //rikthehu tek te dyja metodat
    @PostMapping("/addTicket")
    public ResponseEntity<Ticket> addTicket(@RequestBody Ticket ticket){
        Ticket newTicket = ticketService.addTicket(ticket);
        return new ResponseEntity<>(newTicket,HttpStatus.CREATED);
    }


}
