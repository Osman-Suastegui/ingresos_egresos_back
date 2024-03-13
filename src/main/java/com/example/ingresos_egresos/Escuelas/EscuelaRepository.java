package com.example.ingresos_egresos.Escuelas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface EscuelaRepository extends JpaRepository<Escuela,Long> {

    @Query("SELECT AE.escuela  FROM AsignacionEscuela AE WHERE AE.user.username=?1")
    List<Escuela> findEscuelaByUsername(String username);


}
