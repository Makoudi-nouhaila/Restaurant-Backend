package com.example.demo.entity;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User{


    public Admin() {

    }

    public Admin(String nom, String prenom, String email, String telephone, String password) {
    	super(nom, prenom, email, telephone, password);
    }

}
