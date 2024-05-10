package com.example.ingresos_egresos.Escuelas;

import com.example.ingresos_egresos.Auth.AuthenticationResponse;
import com.example.ingresos_egresos.Auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/asignacionEscuela")
public class AsignacionEscuelaController {

        private final AsignacionEscuelaService asignacionEscuelaService;

        @CrossOrigin(origins = "http://localhost:4200")
        @PostMapping("/addAsignacionEscuela")
        public void addAsignacionEscuela(@RequestBody AddAsignacionEscuelaReq req) {
            asignacionEscuelaService.addAsignacionEscuela(req);
        }

        @CrossOrigin(origins = "http://localhost:4200")
        @GetMapping("/getAsignacionEscuela")
        public int getAsignacionEscuelaByUserName(@RequestParam(name="username") Long username){
            return this.asignacionEscuelaService.getAsignacionEscuelaByUserName(username);
        }


        //eliminar una asignacion de escuela por id de la escuela y id del usuario
        @CrossOrigin(origins = "http://localhost:4200")
        @DeleteMapping("/deleteAsignacionEscuela")
        public void deleteAsignacionEscuela(@RequestParam(name="idEscuela") Long idEscuela, @RequestParam(name="idUsuario") Long idUsuario){
            this.asignacionEscuelaService.deleteAsignacionEscuela(idEscuela,idUsuario);
        }

}
