package com.example.ingresos_egresos.movimientos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento,Long> {
    List<Movimiento> findByFechaBetween(Date fecha1, Date fecha2);
}
