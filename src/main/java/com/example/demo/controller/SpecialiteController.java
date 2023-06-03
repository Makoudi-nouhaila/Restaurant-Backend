package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Specialite;
import com.example.demo.repository.SpecialiteRepository;

@RestController
@RequestMapping("/api/specialites")
@CrossOrigin
public class SpecialiteController {
    
    @Autowired
    private SpecialiteRepository specialiteRepository;
    
    @GetMapping("/all")
    public ResponseEntity<List<Specialite>> getAllSpecialites() {
        List<Specialite> specialites = specialiteRepository.findAll();
        return new ResponseEntity<>(specialites, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Specialite> getSpecialiteById(@PathVariable("id") int id) {
        Optional<Specialite> optionalSpecialite = specialiteRepository.findById(id);
        if (optionalSpecialite.isPresent()) {
            Specialite specialite = optionalSpecialite.get();
            return new ResponseEntity<>(specialite, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/save")
    public ResponseEntity<Specialite> createSpecialite(@RequestBody Specialite specialite) {
        Specialite savedSpecialite = specialiteRepository.save(specialite);
        return new ResponseEntity<>(savedSpecialite, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Specialite> updateSpecialite(@PathVariable("id") int id, @RequestBody Specialite specialite) {
        Optional<Specialite> optionalSpecialite = specialiteRepository.findById(id);
        if (optionalSpecialite.isPresent()) {
            Specialite existingSpecialite = optionalSpecialite.get();
            existingSpecialite.setNom(specialite.getNom());
            
            specialiteRepository.save(existingSpecialite);
            return new ResponseEntity<>(existingSpecialite, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecialite(@PathVariable("id") int id) {
        Optional<Specialite> optionalSpecialite = specialiteRepository.findById(id);
        if (optionalSpecialite.isPresent()) {
            specialiteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
