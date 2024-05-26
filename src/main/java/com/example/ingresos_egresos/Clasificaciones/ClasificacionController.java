package com.example.ingresos_egresos.Clasificaciones;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clasificaciones")
public class ClasificacionController {

    private final ClasificacionService clasificacionService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getClasificaciones")
    public ResponseEntity<List<Clasificacion>> getClasificaciones(){
        return ResponseEntity.ok(clasificacionService.getClasificaciones());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addClasificacion")
    public ResponseEntity<Clasificacion> addClasificacion(@RequestBody Clasificacion clasificacion){
        return ResponseEntity.ok(clasificacionService.addClasificacion(clasificacion));
    }



}
