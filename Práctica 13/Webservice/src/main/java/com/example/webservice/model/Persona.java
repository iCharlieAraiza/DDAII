package com.example.webservice.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "genero")
    private String genero;

    @Column(name = "status")
    private String estatus;
}
