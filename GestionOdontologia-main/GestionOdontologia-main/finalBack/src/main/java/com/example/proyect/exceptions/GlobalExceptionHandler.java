package com.example.proyectoIntegrador.exception;

import com.example.proyectoIntegrador.controller.AppointmentController;
import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@AllArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> badRequest(BadRequestException e){
        logger.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());

    }

    @ExceptionHandler({AppNotFoundException.class})
    public ResponseEntity<String> appNotFound(AppNotFoundException e){
        logger.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

    }
    @ExceptionHandler({AppNoContException.class})
    public ResponseEntity<String> appNoContent(AppNoContException e){
        logger.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());

    }



    @ExceptionHandler({DentistNotFoundException.class})
    public ResponseEntity<String> dentistNotFound(DentistNotFoundException e){
        logger.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

    }
    @ExceptionHandler({DentistNoContException.class})
    public ResponseEntity<String> dentistNoContent(DentistNoContException e){
        logger.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());

    }



    @ExceptionHandler({PatientNotFoundException.class})
    public ResponseEntity<String> patientNotFound(PatientNotFoundException e){
        logger.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

    }
    @ExceptionHandler({PatientNoContentException.class})
    public ResponseEntity<String> patientNoContent(PatientNoContentException e){
        logger.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());

    }



    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<String> userNotFound(UserNotFoundException e){
        logger.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

    }
    @ExceptionHandler({UserNoContException.class})
    public ResponseEntity<String> userNoContent(UserNoContException e){
        logger.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());

    }

}
