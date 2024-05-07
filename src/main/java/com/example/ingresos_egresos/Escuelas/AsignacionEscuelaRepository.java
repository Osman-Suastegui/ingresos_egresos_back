package com.example.ingresos_egresos.Escuelas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;


public interface AsignacionEscuelaRepository extends JpaRepository<AsignacionEscuela, Long>{

    @Query("SELECT count(AE) FROM AsignacionEscuela AE WHERE AE.user.username=?1")
    int findAsignacionEscuelaByUsername(String username);

    //give me the count of the number of schools assigned to a user by his id
    @Query("SELECT count(AE) FROM AsignacionEscuela AE WHERE AE.user.id=?1")
    int findAsignacionEscuelaByIdUser(Long idUser);

}
