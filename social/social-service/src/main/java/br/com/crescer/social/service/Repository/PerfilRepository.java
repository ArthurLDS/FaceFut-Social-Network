/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service.Repository;

import br.com.crescer.social.entity.Perfil;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Arthur
 */

public interface PerfilRepository extends CrudRepository<Perfil, Long>{
    
    
}
