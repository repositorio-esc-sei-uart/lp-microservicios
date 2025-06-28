/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labintec.dev.demo.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author chari
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class RecursoDuplicadoException extends RuntimeException {

    public RecursoDuplicadoException(String message) {
        super(message);
    }
    
}
