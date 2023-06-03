package com.example.demo.repository;

import com.example.demo.entity.Restaurant;
import com.example.demo.entity.Zone;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

	List<Restaurant> findByZone(Zone zone);
	
}
