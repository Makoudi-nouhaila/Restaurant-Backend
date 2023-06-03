package com.example.demo.controller;

import com.example.demo.entity.Ville;
import com.example.demo.entity.Zone;
import com.example.demo.repository.VilleRepository;
import com.example.demo.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("zones")
@CrossOrigin
public class ZoneController {

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private VilleRepository villeRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Zone>> getAllZones() {
        List<Zone> zones = zoneRepository.findAll();
        return new ResponseEntity<>(zones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Zone> getZoneById(@PathVariable("id") int id) {
        Optional<Zone> zone = zoneRepository.findById(id);
        if (zone.isPresent()) {
            return new ResponseEntity<>(zone.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Zone> createZone(@RequestBody Zone zone) {
        Ville ville = villeRepository.findById(zone.getVille().getId());
        if (ville != null) {
            zone.setVille(ville);
            Zone savedZone = zoneRepository.save(zone);
            return new ResponseEntity<>(savedZone, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Zone> updateZone(@PathVariable("id") int id, @RequestBody Zone zone) {
        Optional<Zone> existingZoneOptional = zoneRepository.findById(id);
        if (existingZoneOptional.isPresent()) {
            Zone existingZone = existingZoneOptional.get();
            Ville ville = villeRepository.findById(zone.getVille().getId());
            if (ville != null) {
                existingZone.setNom(zone.getNom());
                existingZone.setVille(ville);
                Zone savedZone = zoneRepository.save(existingZone);
                return new ResponseEntity<>(savedZone, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteZone(@PathVariable("id") int id) {
        Optional<Zone> zone = zoneRepository.findById(id);
        if (zone.isPresent()) {
            zoneRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
