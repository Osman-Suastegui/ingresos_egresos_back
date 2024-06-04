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

        @CrossOrigin(origins = "*")
        @PostMapping("/addMovimiento")
        public void addMovimiento(@Validated @RequestBody AddMovimientoReq movimiento) {
            System.out.println(movimiento);
            movimientoService.addMovimiento(movimiento);
        }
    @CrossOrigin(origins = "*")

    @GetMapping("/getMovimiento")
    public ResponseEntity<GetMovimientoReq> getMovimiento(@RequestParam("idMovimiento") Long idMovimiento,@RequestParam("idEscuela") Long idEscuela) {
        return movimientoService.getMovimiento(idMovimiento,idEscuela);
    }


//   CONSULTA
    @CrossOrigin(origins = "*")
    @GetMapping("/getMovimientosByFechas")
    public ResponseEntity<List<GetMovimientoReq>> getMovimientos(
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") String fechaInicioStr,
            @RequestParam("fechaFinal") @DateTimeFormat(pattern = "yyyy-MM-dd") String fechaFinalStr,
            @RequestParam("idEscuela") Long idEscuela
    ) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fechaInicio = dateFormat.parse(fechaInicioStr);
            Date fechaFinal = dateFormat.parse(fechaFinalStr);

             return movimientoService.getMovimientos(fechaInicio, fechaFinal,idEscuela);


        } catch (ParseException e) {
            // Manejo de excepción si la conversión de fecha falla
            System.out.println("Error al convertir la fecha");
            return ResponseEntity.ok().body(null);
        }
    }




    @CrossOrigin(origins = "*")
    @GetMapping("/generateReport")
    public ResponseEntity<GetReport> getReport(
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") String fechaInicioStr,
            @RequestParam("fechaFinal") @DateTimeFormat(pattern = "yyyy-MM-dd") String fechaFinalStr,
            @RequestParam("idEscuela") Long idEscuela
    ) {


//        {
//            clasificacion1{
//                ingreso: ""
//                egreso: ""
//            }
//        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fechaInicio = dateFormat.parse(fechaInicioStr);
            Date fechaFinal = dateFormat.parse(fechaFinalStr);

            return movimientoService.getReport(fechaInicio, fechaFinal,idEscuela);


        } catch (ParseException e) {
            // Manejo de excepción si la conversión de fecha falla
            System.out.println("Error al convertir la fecha");
            return ResponseEntity.ok().body(null);
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/ingresosEgresosPorClasificacion")
    public ResponseEntity<Object> ingresosEgresosPorClasificacion(
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") String fechaInicioStr,
            @RequestParam("fechaFinal") @DateTimeFormat(pattern = "yyyy-MM-dd") String fechaFinalStr,
            @RequestParam("idEscuela") Long idEscuela
    ) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fechaInicio = dateFormat.parse(fechaInicioStr);
            Date fechaFinal = dateFormat.parse(fechaFinalStr);

            return ResponseEntity.ok().body(movimientoService.ingresosEgresosPorClasificacion(fechaInicio, fechaFinal, idEscuela));
        }
        catch (ParseException e) {
            // Manejo de excepción si la conversión de fecha falla
            System.out.println("Error al convertir la fecha");
            return ResponseEntity.ok().body(null);
        }
    }

}
