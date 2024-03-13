package com.example.ingresos_egresos.Escuelas;

import com.example.ingresos_egresos.users.User;
import jakarta.persistence.*;

@Entity
@Table(name = "asignacion_escuelas")
public class AsignacionEscuela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_escuela")
    private Escuela escuela;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User user;

    // getters and setters
}
