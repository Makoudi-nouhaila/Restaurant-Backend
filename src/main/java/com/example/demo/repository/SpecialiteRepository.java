package com.example.demo.repository;

import com.example.demo.entity.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialiteRepository extends JpaRepository<Specialite, Integer> {
	
}
