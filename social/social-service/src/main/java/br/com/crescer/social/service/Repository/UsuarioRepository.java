/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service.Repository;

import br.com.crescer.social.entity.Perfil;
import br.com.crescer.social.entity.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Arthur
 */
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long>{
   
    Usuario findByEmail(String email);
    
    Usuario findByPerfil(Perfil perfil);
}
