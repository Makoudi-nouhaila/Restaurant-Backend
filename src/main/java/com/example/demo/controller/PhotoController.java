package com.example.demo.controller;


import com.example.demo.entity.Photo;

import com.example.demo.repository.PhotoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

@RestController
@RequestMapping("/api/photos")
@CrossOrigin
public class PhotoController {

    @Autowired
    private PhotoRepository photoRepository;
    
    @GetMapping("/all")
    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Photo getPhotoById(@PathVariable("id") int id) {
        return photoRepository.findById(id).orElseThrow(() -> new NotFoundException("photo not found with id " + id));
    }

    @PostMapping("/save")
    public Photo createPhoto(@RequestBody Photo photo) {
        return photoRepository.save(photo);
    }

    @PutMapping("/{id}")
    public Photo updatePhoto(@PathVariable("id") int id, @RequestBody Photo photo) {
        photo.setId(id);
        return photoRepository.save(photo);
    }

    @DeleteMapping("/{id}")
    public void deletePhoto(@PathVariable("id") int id) {
        photoRepository.deleteById(id);
    }
}
