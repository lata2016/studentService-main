package com.project.schoolService.controller;

import com.project.schoolService.model.LaptopPart;
import com.project.schoolService.service.LaptopPartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/laptopPart")
public class LaptopPartController {
    private LaptopPartService laptopPartService;

    public LaptopPartController(LaptopPartService laptopPartService) {
        this.laptopPartService = laptopPartService;
    }


    @GetMapping("/getAllLaptopParts")
    public ResponseEntity<List<LaptopPart>> getAllLaptopParts() {
        List<LaptopPart> laptopParts = laptopPartService.getAllLaptopParts();
        return new ResponseEntity<>(laptopParts, HttpStatus.OK);
    }
    @GetMapping("/findLaptopPartById/{idPart}")
    public ResponseEntity<LaptopPart> findLaptopPartById(@PathVariable("idPart") Long idPart){
        LaptopPart laptopPart = laptopPartService.getLaptopPartById(idPart);
        return new ResponseEntity<>(laptopPart, HttpStatus.OK);
    }
    @PostMapping("/createLaptopPart")
    public ResponseEntity<LaptopPart> createLaptopPart(@RequestBody LaptopPart laptopPart){
        LaptopPart newLaptopPart = laptopPartService.createLaptopPart(laptopPart);
        return new ResponseEntity<>(newLaptopPart,HttpStatus.CREATED);
    }

    @PostMapping("/updateLaptopPart/{idPart}")
    public ResponseEntity<LaptopPart> updateLaptopPart(@RequestBody LaptopPart laptopPart){
        LaptopPart updateLaptopPart = laptopPartService.updateLaptopPart(laptopPart);
        return new ResponseEntity<>(updateLaptopPart,HttpStatus.OK);
    }


    @DeleteMapping("/delete/{ipPart}")
    public ResponseEntity<?> deleteLaptopPart(@PathVariable("idPart") Long idPart) {
        laptopPartService.deleteLaptopPart(idPart);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}