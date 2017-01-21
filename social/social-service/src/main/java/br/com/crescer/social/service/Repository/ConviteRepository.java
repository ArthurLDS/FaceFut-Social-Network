/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service.Repository;

import br.com.crescer.social.entity.Convite;
import br.com.crescer.social.entity.Perfil;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Arthur
 */
public interface ConviteRepository extends PagingAndSortingRepository<Convite, Long>{
    
    public Iterable<Convite> findByPerfilDestinatario(Perfil destinatario);
    
    public Iterable<Convite> findByPerfilRemetente(Perfil remetente);
    
    public Convite findById(Long id);
    
}
