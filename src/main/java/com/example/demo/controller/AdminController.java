package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;

@RestController
@RequestMapping("/api/admins")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/all")
    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable(value = "id") int id) {
        Optional<Admin> admin = adminRepository.findById(id);
        if(admin.isPresent()) {
            return ResponseEntity.ok().body(admin.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminRepository.save(admin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable(value = "id") int id, @RequestBody Admin adminDetails) {
        Optional<Admin> admin = adminRepository.findById(id);
        if(admin.isPresent()) {
            Admin updatedAdmin = admin.get();
            updatedAdmin.setNom(adminDetails.getNom());
            updatedAdmin.setPrenom(adminDetails.getPrenom());
            updatedAdmin.setEmail(adminDetails.getEmail());
            updatedAdmin.setTelephone(adminDetails.getTelephone());
            updatedAdmin.setPassword(adminDetails.getPassword());
            Admin savedAdmin = adminRepository.save(updatedAdmin);
            return ResponseEntity.ok(savedAdmin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable(value = "id") int id) {
        Optional<Admin> admin = adminRepository.findById(id);
        if(admin.isPresent()) {
            adminRepository.delete(admin.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
