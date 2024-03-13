package com.example.ingresos_egresos.Auth;

import com.example.ingresos_egresos.Rol.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String usuario;
    private String password;
    private String email;
    private int idRol;
}
