/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labintec.dev.demo.controlador;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import labintec.dev.demo.dto.UsuarioDTO;
import labintec.dev.demo.dto.UsuarioMapper;
import labintec.dev.demo.entidad.Usuario;
import labintec.dev.demo.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chari
 */
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:4200"}) // Ambiente desarrollo: LiveServer
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService servicio;
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuarios = servicio.listarTodos();
        List<UsuarioDTO> dtos = new ArrayList();
        
        for (Usuario usuario : usuarios) {
            dtos.add(UsuarioMapper.toDTO(usuario));
        }
        
        return dtos;
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDTO obtenerPorId(@PathVariable Long id){
        Usuario usuario = servicio.obtenerPorId(id);
        return UsuarioMapper.toDTO(usuario);
    }
    
    @GetMapping("/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDTO obtenerPorUsername(@PathVariable String username){
        Usuario usuario = servicio.obtenerPorUsername(username);
        return UsuarioMapper.toDTO(usuario);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO crearUsuario(@Valid @RequestBody UsuarioDTO dto){
        Usuario nuevo = servicio.crear(UsuarioMapper.toEntity(dto));
        return UsuarioMapper.toDTO(nuevo);
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDTO actualizarUsuario(@Valid @RequestBody UsuarioDTO dto){
        Usuario modificado = servicio.actualizar(UsuarioMapper.toEntity(dto));
        return UsuarioMapper.toDTO(modificado);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id){
        servicio.eliminar(id);
    }
    
    @DeleteMapping("/username/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPorUsername(@PathVariable String username){
        servicio.eliminarPorUsername(username);
    }
}
