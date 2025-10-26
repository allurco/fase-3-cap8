package br.com.allur.composador_carbono.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Alertas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
