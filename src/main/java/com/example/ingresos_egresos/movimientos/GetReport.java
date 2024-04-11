package com.example.ingresos_egresos.movimientos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetReport {

    private double ingresos;

    private double egresos;

    private String nombre;

    private String clave;

    private String sector;

    private String zona;

    private String localidad;

}
