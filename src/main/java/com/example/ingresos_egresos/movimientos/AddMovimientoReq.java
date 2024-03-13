package com.example.ingresos_egresos.movimientos;

import com.example.ingresos_egresos.Clasificaciones.Clasificacion;
import com.example.ingresos_egresos.Escuelas.Escuela;
import com.example.ingresos_egresos.users.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddMovimientoReq {

    private Date fecha;
    private TipoDeMovimiento tipoMovimiento;

    @NotBlank
    private String concepto;
    @NotBlank
    private String motivo;
    @NotBlank
    private String persona;
    private int importe;
    private Long idEscuela;
    private Long idClasificacion;
    private Long idUsuario;
}
