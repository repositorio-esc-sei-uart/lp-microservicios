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
    
    public Usuario crear(Usuario nuevo) {
        Optional<Usuario> opcional = repositorio.findByUsername(nuevo.getUsername());
        
        if(opcional.isPresent()){
            throw new RecursoDuplicadoException("Usuario " + nuevo.getUsername()
            + " ya existe.");
        }
            
        return repositorio.save(nuevo);
    } 
    
    public Usuario actualizar(Usuario existente){
        Optional<Usuario> opcional = repositorio.findById(existente.getCoduser());
        
        if(!opcional.isPresent()) {
            throw new RecursoNoEncontradoException("El usuario " 
                    + existente + " no existe.");
        }
        
        return repositorio.save(existente);
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
