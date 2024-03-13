package com.example.ingresos_egresos.movimientos;

import com.example.ingresos_egresos.Clasificaciones.Clasificacion;
import com.example.ingresos_egresos.Clasificaciones.ClasificacionRepository;
import com.example.ingresos_egresos.Escuelas.Escuela;
import com.example.ingresos_egresos.Escuelas.EscuelaRepository;
import com.example.ingresos_egresos.users.User;
import com.example.ingresos_egresos.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        mov.setEscuela(esc.get());
        Optional<User> user = userRepository.findById(movimientoReq.getIdUsuario());
        mov.setUser(user.get());
        mov.setPersona(movimientoReq.getPersona());

        movimientoRepository.save(mov);

    }

    public void deleteMovimiento(Movimiento movimiento) {
    }

    public void updateMovimiento(Movimiento movimiento) {
    }

    public ResponseEntity<GetMovimientoReq> getMovimiento(Long idMovimiento) {
        Optional<Movimiento> mov = movimientoRepository.findById(idMovimiento);
        Movimiento movimiento = mov.get();
        GetMovimientoReq  getMovReq = new GetMovimientoReq();

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

    public ResponseEntity<List<GetMovimientoReq>> getMovimientos(Date fechaInicio, Date fechaFinal) {
        List<Movimiento> movs = movimientoRepository.findByFechaBetween(fechaInicio, fechaFinal);
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

}
