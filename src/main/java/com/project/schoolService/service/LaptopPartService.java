package com.project.schoolService.service;

import com.project.schoolService.model.LaptopPart;
import com.project.schoolService.model.Ticket;
import com.project.schoolService.repository.LaptopPartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaptopPartService {
    private LaptopPartRepository laptopPartRepository;

    public LaptopPartService (LaptopPartRepository laptopPartRepository){
        this.laptopPartRepository = laptopPartRepository;
    }
    //CRUD METHODS
    public List<LaptopPart> getAllLaptopParts(){
        return laptopPartRepository.findAll();
    }
    public LaptopPart createLaptopPart(Long idPart, String name, String description, Double price, Double stock, List<Ticket> tickets) {
        LaptopPart laptopPart = new LaptopPart();
        laptopPart.setIdPart(idPart);
        laptopPart.setName(name);
        laptopPart.setDescription(description);
        laptopPart.setPrice(price);
        laptopPart.setStock(stock);
        laptopPart.setTickets(tickets);

        return laptopPartRepository.save(laptopPart);
    }
    public LaptopPart createLaptopPart(LaptopPart laptopPart){
        return laptopPartRepository.save(laptopPart);
    }

    public LaptopPart getLaptopPartById(Long id) {
        return laptopPartRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid laptop part ID"));
    }

    public LaptopPart updateLaptopPart(LaptopPart laptopPart) {
        return laptopPartRepository.save(laptopPart);
    }
    public void deleteLaptopPart(Long idPart){
        laptopPartRepository.deleteById(idPart);
    }
}
