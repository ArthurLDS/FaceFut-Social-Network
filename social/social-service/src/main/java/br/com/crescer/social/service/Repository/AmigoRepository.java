/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service.Repository;

import antlr.collections.List;
import br.com.crescer.social.entity.Amigo;
import br.com.crescer.social.entity.Perfil;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Arthur
 */
public interface AmigoRepository extends PagingAndSortingRepository<Amigo, Long> {

    public Amigo findByPerfil(Perfil perfil);
    
    public Amigo findById(Long id);
    
}
