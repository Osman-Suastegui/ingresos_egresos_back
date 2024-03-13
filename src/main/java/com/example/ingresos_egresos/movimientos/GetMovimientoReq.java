package com.example.ingresos_egresos.movimientos;


import lombok.Data;

import java.util.Date;

@Data
public class GetMovimientoReq {

    private String concepto;
    private String motivo;
    private String persona;
    private double importe;
    private Date fecha;
    private TipoDeMovimiento tipoMovimiento;
    private String username;
    private String clasificacion;
    private Long idClasificacion;
}
