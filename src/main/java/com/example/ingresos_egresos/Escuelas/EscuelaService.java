package com.example.ingresos_egresos.Escuelas;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class EscuelaService {

    private final EscuelaRepository escuelaRepository;



    public void addEscuela(AddEscuelaReq req) {
        Escuela esc  = new Escuela();
        esc.setBalance(0);
        esc.setZona(req.getZona());
        esc.setSector(req.getSector());
        esc.setLocalidad(req.getLocalidad());
        esc.setNombre(req.getNombre());
        esc.setClave(req.getClave());
        escuelaRepository.save(esc);
    }

    public Escuela getEscuela(Long idEscuela) {
        Optional<Escuela> esc = escuelaRepository.findById(idEscuela);
        return esc.get();

    }

    public List<Escuela> getEscuelasByUserName(String username) {
        return escuelaRepository.findEscuelaByUsername(username);
    }

    public List<Escuela> getEscuelas() {
        return escuelaRepository.findAll();
    }
}
