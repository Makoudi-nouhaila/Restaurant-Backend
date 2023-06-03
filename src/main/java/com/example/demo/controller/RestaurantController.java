package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

import com.example.demo.entity.Restaurant;
import com.example.demo.repository.RestaurantRepository;

@RestController
@RequestMapping("/api/restaurants")
@CrossOrigin
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantService;

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.findAll();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable("id") int id) {
        Restaurant restaurant = restaurantService.findById(id).orElseThrow(() -> new NotFoundException("Restaurant not found with id " + id));
        if (restaurant != null) {
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant savedRestaurant = restaurantService.save(restaurant);
        return new ResponseEntity<>(savedRestaurant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable("id") int id, @RequestBody Restaurant restaurant) {
        restaurant.setId(id);
        Restaurant updatedRestaurant = restaurantService.save(restaurant);
        if (updatedRestaurant != null) {
            return new ResponseEntity<>(updatedRestaurant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

  //  @DeleteMapping("/{id}")
   // public ResponseEntity<Void> deleteRestaurant(@PathVariable("id") int id) {
       // boolean deleted = restaurantService.deleteById(id);
       // if (deleted) {
          //  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       // } else {
         //   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       // }
   // }

}

