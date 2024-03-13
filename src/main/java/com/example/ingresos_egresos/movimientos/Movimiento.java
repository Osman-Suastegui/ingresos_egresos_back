package com.example.ingresos_egresos.movimientos;
import com.example.ingresos_egresos.Clasificaciones.Clasificacion;
import com.example.ingresos_egresos.Escuelas.Escuela;
import com.example.ingresos_egresos.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "movimientos")
@Data
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fecha;

    @Enumerated(EnumType.STRING)
    private TipoDeMovimiento tipoMovimiento;

    private String concepto;
    private String motivo;
    private String persona;
    private double importe;

    @ManyToOne
    @JoinColumn(name = "id_escuela")
    private Escuela escuela;

    @ManyToOne
    @JoinColumn(name = "id_clasificacion")
    private Clasificacion clasificacion;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User user;

}