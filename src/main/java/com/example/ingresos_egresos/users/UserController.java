package com.example.ingresos_egresos.users;

import com.example.ingresos_egresos.Escuelas.Escuela;
import com.example.ingresos_egresos.Escuelas.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @CrossOrigin(origins = "*")
    @GetMapping("/getUsuarios")
    public ResponseEntity<List<User>> getUsuarios(){
        List<User> usu = this.userService.getUsers();
        return ResponseEntity.ok(usu);
    }
}
