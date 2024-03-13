package com.example.ingresos_egresos.movimientos;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/Movimientos")
@RequiredArgsConstructor
public class MovimientoController {

        private final MovimientoService movimientoService;

        @CrossOrigin(origins = "http://localhost:4200")
        @PostMapping("/addMovimiento")
        public void addMovimiento(@Validated @RequestBody AddMovimientoReq movimiento) {
            System.out.println(movimiento);
            movimientoService.addMovimiento(movimiento);
        }
    @CrossOrigin(origins = "http://localhost:4200")

    @GetMapping("/getMovimiento")
    public ResponseEntity<GetMovimientoReq> getMovimiento(@RequestParam("idMovimiento") Long idMovimiento) {
        return movimientoService.getMovimiento(idMovimiento);
    }


    //    CONSULTA
//    @CrossOrigin(origins = "http://localhost:4200")
//    @GetMapping("/getMovimientos")
//    public ResponseEntity<List<GetMovimientoReq>> getMovimientos(@RequestParam("fechaInicio") Date fechaInicio,@RequestParam("fechaFinal") Date fechaFinal){
//        return movimientoService.getMovimiento(idMovimiento);
//    }

}
