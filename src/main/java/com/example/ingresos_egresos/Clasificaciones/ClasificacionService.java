package com.example.ingresos_egresos.Clasificaciones;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ClasificacionService {

    private final ClasificacionRepository clasificacionRepository;
    public List<Clasificacion> getClasificaciones(){
        return clasificacionRepository.findAll();
    }
}
