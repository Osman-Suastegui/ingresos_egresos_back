package com.example.ingresos_egresos.HandleErrors;

public class ErrorResponse {

    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    // Getters and setters (optional)

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}