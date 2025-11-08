package com.todo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {
//   Address: street, town/ city, state, zip code, country
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String street;
    private String city;
    private String state;
    private int zip;
    private String country;
}
