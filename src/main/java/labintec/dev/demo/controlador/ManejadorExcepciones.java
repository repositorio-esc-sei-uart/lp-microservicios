/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labintec.dev.demo.controlador;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import labintec.dev.demo.excepcion.RecursoDuplicadoException;
import labintec.dev.demo.excepcion.RecursoNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author chari
 */
@RestControllerAdvice
public class ManejadorExcepciones {
    
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> 
        manejarRecursoNoEncontrado(RecursoNoEncontradoException e,
                HttpServletRequest request){
        
        Map<String, Object> error = new HashMap();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("error", "Recurso no encontrado");
        error.put("message", e.getMessage());
        error.put("path", request.getRequestURI());
             
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(RecursoDuplicadoException.class)
    public ResponseEntity<Map<String, Object>>
            manejarRecursoDuplicado(RecursoDuplicadoException e, 
                    HttpServletRequest request){
    
        Map<String, Object> error = new HashMap();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.CONFLICT.value());
        error.put("error", "Recurso duplicado");
        error.put("message", e.getMessage());
        error.put("path", request.getRequestURI());  

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
