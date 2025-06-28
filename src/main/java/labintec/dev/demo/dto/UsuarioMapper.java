/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labintec.dev.demo.dto;

import labintec.dev.demo.entidad.Usuario;

/**
 *
 * @author chari
 */
public class UsuarioMapper {
   public static UsuarioDTO toDTO(Usuario usuario) {
       UsuarioDTO dto = new UsuarioDTO();
       dto.setCoduser(usuario.getCoduser());
       dto.setUsername(usuario.getUsername());
       dto.setEmail(usuario.getEmail());
       return dto;       
   } 
   
   public static Usuario toEntity(UsuarioDTO dto) {
       Usuario entity = new Usuario();
       entity.setCoduser(dto.getCoduser());
       entity.setUsername(dto.getUsername());
       entity.setEmail(dto.getEmail());
       
       return entity;
   }
}
