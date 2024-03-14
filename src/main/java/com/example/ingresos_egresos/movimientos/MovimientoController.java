package com.example.ingresos_egresos.movimientos;


import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public ResponseEntity<GetMovimientoReq> getMovimiento(@RequestParam("idMovimiento") Long idMovimiento,@RequestParam("idEscuela") Long idEscuela) {
        return movimientoService.getMovimiento(idMovimiento,idEscuela);
    }


//   CONSULTA
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getMovimientosByFechas")
    public ResponseEntity<List<GetMovimientoReq>> getMovimientos(
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") String fechaInicioStr,
            @RequestParam("fechaFinal") @DateTimeFormat(pattern = "yyyy-MM-dd") String fechaFinalStr
    ) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fechaInicio = dateFormat.parse(fechaInicioStr);
            Date fechaFinal = dateFormat.parse(fechaFinalStr);

             return movimientoService.getMovimientos(fechaInicio, fechaFinal);


        } catch (ParseException e) {
            // Manejo de excepción si la conversión de fecha falla
            System.out.println("Error al convertir la fecha");
            return ResponseEntity.ok().body(null);
        }
    }

}
