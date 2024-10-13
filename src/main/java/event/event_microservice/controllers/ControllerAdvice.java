package event.event_microservice.controllers;

import event.event_microservice.exceptions.EventFullException;
import event.event_microservice.exceptions.EventNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(EventFullException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String EventFullException(EventFullException e) {
        return e.getMessage();
    }

    @ExceptionHandler(EventNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String EventFullException(EventNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(ConnectException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String busyServer() {
        return "Servidor fora do ar";
    }
}