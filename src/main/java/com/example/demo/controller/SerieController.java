package com.example.demo.controller;

import com.example.demo.entity.Serie;
import com.example.demo.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/series")
@CrossOrigin
public class SerieController {

    @Autowired
    SerieRepository serieRepository;

    // Create operation
    @PostMapping("/save")
    public Serie createSerie(@RequestBody Serie serie) {
        return serieRepository.save(serie);
    }

    // Read operations
    @GetMapping("/all")
    public List<Serie> getAllSeries() {
        return serieRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Serie> getSerieById(@PathVariable("id") int id) {
        return serieRepository.findById(id);
    }

    // Update operation
    @PutMapping("/{id}")
    public Serie updateSerie(@PathVariable("id") int id, @RequestBody Serie serie) {
        Optional<Serie> existingSerie = serieRepository.findById(id);
        if (existingSerie.isPresent()) {
            Serie updatedSerie = existingSerie.get();
            updatedSerie.setNom(serie.getNom());
            return serieRepository.save(updatedSerie);
        } else {
            return null;
        }
    }

    // Delete operation
    @DeleteMapping("/{id}")
    public void deleteSerie(@PathVariable("id") int id) {
        serieRepository.deleteById(id);
    }
}
