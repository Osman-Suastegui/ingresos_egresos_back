package com.example.ingresos_egresos.Escuelas;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddEscuelaReq {
    @NotBlank
    private String sector;
    @NotBlank
    private String nombre;
    @NotBlank
    private String zona;
    @NotBlank
    private String localidad;
    @NotBlank
    private String clave;
}
