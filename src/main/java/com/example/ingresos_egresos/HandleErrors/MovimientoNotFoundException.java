package com.example.ingresos_egresos.HandleErrors;

public class MovimientoNotFoundException extends RuntimeException{

        public MovimientoNotFoundException(String message) {
            super(message);
        }
}
