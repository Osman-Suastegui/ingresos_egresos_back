package com.example.ingresos_egresos.Escuelas;


import com.example.ingresos_egresos.users.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor


public class AsignacionEscuelaService {

    private final AsignacionEscuelaRepository asignacionEscuelaRepository;
    private final EscuelaRepository escuelaRepository;
    private final UserRepository userRepository;

    public void addAsignacionEscuela(AddAsignacionEscuelaReq req) {
        AsignacionEscuela asignacionEscuela = new AsignacionEscuela();
        asignacionEscuela.setEscuela(req.getId_escuela());
        asignacionEscuela.setUser(req.getId_usuario());
        asignacionEscuelaRepository.save(asignacionEscuela);
    }

    public int getAsignacionEscuelaByUserName(Long idUser) {
        return asignacionEscuelaRepository.findAsignacionEscuelaByIdUser(idUser);
    }

    @Transactional
    public void deleteAsignacionEscuela(Long idEscuela, Long idUsuario) {
        asignacionEscuelaRepository.deleteAsignacionEscuelaByEscuelaAndUser(idEscuela,idUsuario);
    }
}
