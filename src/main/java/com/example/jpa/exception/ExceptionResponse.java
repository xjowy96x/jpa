package com.example.jpa.exception;

import lombok.Data;

import java.util.Date;

public class ExceptionResponse {
    private Date timestamp;
    private String mensaje;
    private String detalles;
    private int httpCode;

    public ExceptionResponse(Date timestamp, String message, String details,int httpCode) {
        super();
        this.timestamp = timestamp;
        this.mensaje = message;
        this.detalles = details;
        this.httpCode = httpCode;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getDetalles() {
        return detalles;
    }

}
