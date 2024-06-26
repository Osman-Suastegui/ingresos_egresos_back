package com.example.ingresos_egresos.movimientos;

import com.example.ingresos_egresos.Clasificaciones.Clasificacion;
import com.example.ingresos_egresos.Clasificaciones.ClasificacionRepository;
import com.example.ingresos_egresos.Escuelas.Escuela;
import com.example.ingresos_egresos.Escuelas.EscuelaRepository;
import com.example.ingresos_egresos.HandleErrors.MovimientoNotFoundException;
import com.example.ingresos_egresos.users.User;
import com.example.ingresos_egresos.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final ClasificacionRepository clasificacionRepository;
    private final EscuelaRepository escuelaRepository;
    private final UserRepository userRepository;

    public void addMovimiento(AddMovimientoReq movimientoReq) {
        Movimiento mov = new Movimiento();
        mov.setFecha(movimientoReq.getFecha());
        mov.setTipoMovimiento(movimientoReq.getTipoMovimiento());
        mov.setImporte(movimientoReq.getImporte());
        mov.setConcepto(movimientoReq.getConcepto());
        mov.setMotivo(movimientoReq.getMotivo());

        Optional<Clasificacion> clas = clasificacionRepository.findById(movimientoReq.getIdClasificacion());
        mov.setClasificacion(clas.get());
        Optional<Escuela> esc = escuelaRepository.findById(movimientoReq.getIdEscuela());
        if(esc.isEmpty()) throw new MovimientoNotFoundException("Escuela no encontrada");
        Escuela escuela = esc.get();
        if(movimientoReq.getTipoMovimiento().equals(TipoDeMovimiento.EGRESO)) {

            escuela.setBalance(escuela.getBalance() - movimientoReq.getImporte());
        }else{
            escuela.setBalance(escuela.getBalance() + movimientoReq.getImporte());
        }

        mov.setEscuela(escuela);
        Optional<User> user = userRepository.findById(movimientoReq.getIdUsuario());
        mov.setUser(user.get());
        mov.setPersona(movimientoReq.getPersona());

        escuelaRepository.save(escuela);
        movimientoRepository.save(mov);

    }

    public void deleteMovimiento(Movimiento movimiento) {
    }

    public void updateMovimiento(Movimiento movimiento) {
    }

    public ResponseEntity<GetMovimientoReq> getMovimiento(Long idMovimiento, Long idEscuela) {
        Movimiento mov = movimientoRepository.findByIdAndAndEscuelaId(idMovimiento, idEscuela);
        if (mov == null) throw new MovimientoNotFoundException("Movimiento no encontrado");
        Movimiento movimiento = mov;
        GetMovimientoReq getMovReq = new GetMovimientoReq();

        getMovReq.setMotivo(movimiento.getMotivo());
        getMovReq.setTipoMovimiento(movimiento.getTipoMovimiento());
        getMovReq.setConcepto(movimiento.getConcepto());
        getMovReq.setUsername(movimiento.getUser().getUsername());
        getMovReq.setImporte(movimiento.getImporte());
        getMovReq.setFecha(movimiento.getFecha());
        getMovReq.setPersona(movimiento.getPersona());
        getMovReq.setClasificacion(movimiento.getClasificacion().getNombre());
        getMovReq.setIdClasificacion(movimiento.getClasificacion().getId());
        return ResponseEntity.ok(getMovReq);

    }

    public ResponseEntity<List<GetMovimientoReq>> getMovimientos(Date fechaInicio, Date fechaFinal, Long idEscuela) {
        List<Movimiento> movs = movimientoRepository.findByFechaBetweenAndEscuelaId(fechaInicio, fechaFinal, idEscuela);
        List<GetMovimientoReq> getMovs = new ArrayList<>();
        for (Movimiento mov : movs) {
            GetMovimientoReq getMov = new GetMovimientoReq();
            getMov.setMotivo(mov.getMotivo());
            getMov.setTipoMovimiento(mov.getTipoMovimiento());
            getMov.setConcepto(mov.getConcepto());
            getMov.setUsername(mov.getUser().getUsername());
            getMov.setImporte(mov.getImporte());
            getMov.setFecha(mov.getFecha());
            getMov.setPersona(mov.getPersona());
            getMov.setClasificacion(mov.getClasificacion().getNombre());
            getMov.setIdClasificacion(mov.getClasificacion().getId());
            getMovs.add(getMov);
        }
        return ResponseEntity.ok(getMovs);
    }

    //metodo para obtener los movimientos de ingresos y egresos de una escuela entre dos rangos de fechas

    public ResponseEntity<GetReport> getReport(Date fechaInicio, Date fechaFinal, Long idEscuela) {
        List<Movimiento> movs = movimientoRepository.findByFechaBetweenAndEscuelaId(fechaInicio, fechaFinal, idEscuela);
        //obten los ingresos y egresos de esos movs
        double ingresos = 0;
        double egresos = 0;
        for (Movimiento mov : movs) {
            if (mov.getTipoMovimiento().equals(TipoDeMovimiento.INGRESO)) {
                ingresos += mov.getImporte();
            } else {
                egresos += mov.getImporte();
            }
        }
        GetReport report = new GetReport();

        report.setIngresos(ingresos);
        report.setEgresos(egresos);
        Optional<Escuela> esc = escuelaRepository.findById(idEscuela);
        report.setNombre(esc.get().getNombre());
        report.setClave(esc.get().getClave());
        report.setSector(esc.get().getSector());
        report.setZona(esc.get().getZona());
        report.setLocalidad(esc.get().getLocalidad());

        return ResponseEntity.ok(report);
    }


//            clasificacion1{
//                ingreso: 1000
//                egreso: 100
//            },
//            clasificacion2{
//                ingreso: 2000
//                egreso: 200
//              }
//        }

    public HashMap<String,Object> ingresosEgresosPorClasificacion(Date fechaInicio, Date fechaFinal, Long idEscuela){

        // Obtener todos los movimientos dentro del periodo y de esa escuela
        // Separar todos los que son egresos e ingresos
        // Separar por clasificacion cada egreso e ingreso
        // Totalizar por clasificacion cada egreso e ingreso   -- >

        List<Movimiento> movs = movimientoRepository.findByFechaBetweenAndEscuelaId(fechaInicio, fechaFinal, idEscuela);
        HashMap<String,Object> clasificaciones = new HashMap<>();

        for (Movimiento mov : movs) {

            if(clasificaciones.containsKey(mov.getClasificacion().getNombre())){
                HashMap<String,Double> ingresosEgresos = (HashMap<String, Double>) clasificaciones.get(mov.getClasificacion().getNombre());
                if(mov.getTipoMovimiento().equals(TipoDeMovimiento.INGRESO)){
                    ingresosEgresos.put("ingreso",ingresosEgresos.get("ingreso")+mov.getImporte());
                }else{
                    ingresosEgresos.put("egreso",ingresosEgresos.get("egreso")+mov.getImporte());
                }
            }else{

                HashMap<String,Double> ingresosEgresos = new HashMap<>();
                if(mov.getTipoMovimiento().equals(TipoDeMovimiento.INGRESO)){
                    ingresosEgresos.put("ingreso",mov.getImporte());
                    ingresosEgresos.put("egreso",0.0);
                }else{
                    ingresosEgresos.put("ingreso",0.0);
                    ingresosEgresos.put("egreso",mov.getImporte());
                }
                clasificaciones.put(mov.getClasificacion().getNombre(),ingresosEgresos);
            }
        }
        return clasificaciones;
    }

}
