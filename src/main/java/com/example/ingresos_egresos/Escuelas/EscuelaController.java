package com.example.ingresos_egresos.Escuelas;

import com.example.ingresos_egresos.Auth.AuthenticationResponse;
import com.example.ingresos_egresos.Auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/escuelas")
public class EscuelaController {

    private final EscuelaService escuelaService;



    @CrossOrigin(origins = "*")
    @PostMapping("/addEscuela")
    public void addEscuela(@RequestBody AddEscuelaReq req) {
        escuelaService.addEscuela(req);


    }

    @CrossOrigin(origins = "*")

    @GetMapping("/getEscuela")
    public ResponseEntity<Escuela> getEscuela(@RequestParam(name="idEscuela") Long idEscuela){
        Escuela esc = escuelaService.getEscuela(idEscuela);
        return ResponseEntity.ok(esc);
    }
    @CrossOrigin(origins = "*")

    @GetMapping("/getEscuelas")
    public ResponseEntity<List<Escuela>> getEscuelasByUserName(@RequestParam(name="username") String username){
        List<Escuela> esc = this.escuelaService.getEscuelasByUserName(username);
        return ResponseEntity.ok(esc);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getAllEscuelas")
    public ResponseEntity<List<Escuela>> getEscuelas(){
        List<Escuela> esc = this.escuelaService.getEscuelas();
        return ResponseEntity.ok(esc);
    }





}
