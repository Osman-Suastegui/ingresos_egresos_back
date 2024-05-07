package com.example.ingresos_egresos.Escuelas;

import com.example.ingresos_egresos.users.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddAsignacionEscuelaReq {

    @NotBlank
    private Escuela id_escuela;
    @NotBlank
    private User id_usuario;

}
