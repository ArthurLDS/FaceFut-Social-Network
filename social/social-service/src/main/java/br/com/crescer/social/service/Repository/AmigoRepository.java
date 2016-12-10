/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service.Repository;

import br.com.crescer.social.entity.Amigo;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Arthur
 */
public interface AmigoRepository extends PagingAndSortingRepository<Amigo, Long>{
    
    Iterable<Amigo> findByEmail(String email);
    
    Iterable<Amigo> findByNome(String nome);
}
