/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labintec.dev.demo.servicio;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import labintec.dev.demo.entidad.Usuario;
import labintec.dev.demo.excepcion.RecursoDuplicadoException;
import labintec.dev.demo.excepcion.RecursoNoEncontradoException;
import labintec.dev.demo.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chari
 */
@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository repositorio;
    
    public List<Usuario> listarTodos(){
        return repositorio.findAll();
    }
    
    public Usuario obtenerPorId(Long id){
        Optional<Usuario> opcional = repositorio.findById(id);
        
        if(!opcional.isPresent()) {
            throw new RecursoNoEncontradoException("Usuario con código "
            + id + " no existe.");
        }
        return opcional.get();
    }
    
    public Usuario obtenerPorUsername(String username) {
        Optional<Usuario> opcional = repositorio.findByUsername(username);
        
        if(!opcional.isPresent()) {
            throw new RecursoNoEncontradoException("Usuario "
            + username + " no existe.");
        }
        return opcional.get();
    }
    
    public Usuario obtenerPorEmail(String email) {
        Optional<Usuario> opcional = repositorio.findByEmail(email);
        
        if(!opcional.isPresent()) {
            throw new RecursoNoEncontradoException("No existe usuario con email "
            + email);
        }
        return opcional.get();
    }
    
    public Usuario crear(Usuario nuevo) {
        Optional<Usuario> opcional = repositorio.findByUsername(nuevo.getUsername());
        
        if(opcional.isPresent()){
            throw new RecursoDuplicadoException("Usuario " + nuevo.getUsername()
            + " ya existe.");
        }
            
        return repositorio.save(nuevo);
    } 
    
    public Usuario actualizar(Usuario modificado){
        // usuario original antes de modificar
        Optional<Usuario> original = repositorio.findById(modificado.getCoduser());
        // en el caso que se haya modificado el username, buscar si ya existe otro usuario con ese username
        Optional<Usuario> opcionalByUsername = repositorio.findByUsername(modificado.getUsername());
        // en el caso que se haya modificado el email, buscar si ya existe ese email registrado
        Optional<Usuario> opcionalByEmail = repositorio.findByEmail(modificado.getEmail());
      
        if(original.isPresent()) { 
            if(opcionalByUsername.isPresent()) {
                if(!opcionalByUsername.get().getUsername().equals(original.get().getUsername())) {
                    throw new RecursoDuplicadoException("El usuario " + modificado.getUsername()
                    + " ya existe.");
                }
            }
            if(opcionalByEmail.isPresent()) {
                if(!opcionalByEmail.get().getEmail().equals(original.get().getEmail())) {
                    throw new RecursoDuplicadoException("El email " + modificado.getEmail()
                    + " ya se encuentra en uso.");
                }
            }
        } else {
            throw new RecursoNoEncontradoException("El usuario " + original.get().getClass()
                    + " no existe.");
        }
        return repositorio.save(modificado);
    }
    
    public void eliminar(Long id) {
        Optional<Usuario> opcional = repositorio.findById(id);
        
        if(!opcional.isPresent()) {
            throw new RecursoNoEncontradoException("El usuario con id " 
                    + id + " no existe.");
        }
        
        repositorio.deleteById(id);
    }
    
    @Transactional
    public void eliminarPorUsername(String username) {
        Optional<Usuario> opcional = repositorio.findByUsername(username);
        
        if(!opcional.isPresent()) {
            throw new RecursoNoEncontradoException("El usuario " 
                    + username + " no existe.");
        }
        
        repositorio.deleteByUsername(username);
        
    }
}
