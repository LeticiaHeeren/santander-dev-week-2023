package me.dio.domain.model;

import jakarta.persistence.*;

@Entity(name = "tb_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;
    private String state;
    private String zipCode;

    // Getters e Setters
}