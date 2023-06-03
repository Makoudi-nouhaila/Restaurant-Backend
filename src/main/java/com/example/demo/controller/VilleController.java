package com.example.demo.controller;

import com.example.demo.entity.Restaurant;
import com.example.demo.entity.Ville;
import com.example.demo.entity.Zone;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.repository.VilleRepository;
import com.example.demo.repository.ZoneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("villes")
@CrossOrigin
public class VilleController {

	@Autowired
	private VilleRepository villeRepository;
	@Autowired
	private ZoneRepository zoneRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;


	@GetMapping("/all")
	public List<Ville> getAllVilles() {
		return villeRepository.findAll();
	}

	@GetMapping("/{id}")
	public Ville getVilleById(@PathVariable int id) {
		return villeRepository.findById(id);
	}

	@PostMapping("/save")
	public void addVille(@RequestBody Ville ville) {
		villeRepository.save(ville);
	}

	@PutMapping("/{id}")
	public void updateVille(@PathVariable int id, @RequestBody Ville ville) {
		ville.setId(id);
		villeRepository.save(ville);
	}

	@DeleteMapping("/{id}")
	public void deleteVille(@PathVariable int id) {
		villeRepository.deleteById(id);
	}

	@GetMapping("/{nomVille}/zones")
	public List<Zone> getZonesByVille(@PathVariable String nomVille) {
		Ville ville = villeRepository.findByNom(nomVille);
		return zoneRepository.findByVille(ville);
	}
	
	@GetMapping("/{nomVille}/zones/{nomZone}/restaurants")
	public List<Restaurant> getRestaurantsByZone(@PathVariable String nomVille, @PathVariable String nomZone) {
	    Ville ville = villeRepository.findByNom(nomVille);
	    Zone zone = zoneRepository.findByNomAndVille(nomZone, ville);
	    return restaurantRepository.findByZone(zone);
	}


}
