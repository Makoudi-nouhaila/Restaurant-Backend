package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.entity.Ville;
import com.example.demo.entity.Zone;

public interface ZoneRepository extends JpaRepository<Zone, Integer> {

	List<Zone> findByVille(Ville ville);

	Zone findByNomAndVille(String nomZone, Ville ville);
	
}
