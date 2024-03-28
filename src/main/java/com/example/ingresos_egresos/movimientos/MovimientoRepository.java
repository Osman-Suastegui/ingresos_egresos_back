package com.example.ingresos_egresos.movimientos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento,Long> {
//    List<Movimiento> findByFechaBetween(Date fecha1, Date fecha2);
    List<Movimiento> findByFechaBetweenAndEscuelaId(Date fecha1, Date fecha2, Long idEscuela);

    @Query("SELECT m FROM Movimiento m WHERE m.id = ?1 AND m.escuela.id = ?2")
    Movimiento findByIdAndAndEscuelaId(Long id, Long idEscuela);

}
