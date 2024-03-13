package com.example.ingresos_egresos.Escuelas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "escuelas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Escuela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;


    @Column(unique=true)
    private String clave;

    private double balance;

    @Column
    private String sector;
    @Column
    private String zona;

    @Column
    private String localidad;

}