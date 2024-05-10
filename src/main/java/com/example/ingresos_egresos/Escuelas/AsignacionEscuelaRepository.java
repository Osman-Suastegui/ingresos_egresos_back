package com.example.ingresos_egresos.Escuelas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;


public interface AsignacionEscuelaRepository extends JpaRepository<AsignacionEscuela, Long>{

    @Query("SELECT count(AE) FROM AsignacionEscuela AE WHERE AE.user.username=?1")
    int findAsignacionEscuelaByUsername(String username);

    //give me the count of the number of schools assigned to a user by his id
    @Query("SELECT count(AE) FROM AsignacionEscuela AE WHERE AE.user.id=?1")
    int findAsignacionEscuelaByIdUser(Long idUser);


//    void deleteAsignacionEscuelaByEscuelaAndUser(Long idEscuela, Long idUsuario);

    //haz un query que me haga un delete de la asignacion de escuela por id de la escuela y id del usuario
    @Modifying
    @Query("DELETE FROM AsignacionEscuela AE WHERE AE.escuela.id = :idEscuela AND AE.user.id = :idUsuario")
    void deleteAsignacionEscuelaByEscuelaAndUser(@Param("idEscuela") Long idEscuela, @Param("idUsuario") Long idUsuario);


}
