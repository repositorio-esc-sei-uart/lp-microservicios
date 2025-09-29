/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package labintec.dev.demo.repositorio;

import java.util.Optional;
import labintec.dev.demo.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author chari
 */

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByEmail(String email);
    void deleteByUsername(String username);
}
