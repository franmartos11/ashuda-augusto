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

    /*Dentist*/
    @ExceptionHandler({DentistNotFoundException.class})
    public ResponseEntity<String> dentistNotFound(DentistNotFoundException e){
        logger.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler({DentistNoContentException.class})
    public ResponseEntity<String> dentistNoContent(DentistNoContentException e){
        logger.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
    }

    /*Patient*/
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

    /*Appointment*/
    @ExceptionHandler({AppointmentNotFoundException.class})
    public ResponseEntity<String> appointmentNotFound(AppointmentNotFoundException e){
        logger.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler({AppointmentNotContentException.class})
    public ResponseEntity<String> appointmentNoContent(AppointmentNotContentException e){
        logger.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
    }

    /*User*/
    @ExceptionHandler({AppUserNotFoundException.class})
    public ResponseEntity<String> appUserNotFound(AppUserNotFoundException e){
        logger.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler({AppUserNoContentException.class})
    public ResponseEntity<String> appUserNoContent(AppUserNoContentException e){
        logger.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
    }

}
