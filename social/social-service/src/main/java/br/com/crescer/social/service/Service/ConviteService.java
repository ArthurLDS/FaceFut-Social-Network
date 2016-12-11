/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service.Service;

import br.com.crescer.social.entity.Convite;
import br.com.crescer.social.service.Repository.ConviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Arthur
 */
@Service
public class ConviteService {
    
    @Autowired
    ConviteRepository repository;
    
    public Iterable<Convite> findAll(){
        return repository.findAll();
    }
    
    public void save(Convite convite){
        repository.save(convite);
    }
    
    public Iterable<Convite> findByDestinatario(String destinatario){
        return repository.findByDestinatario(destinatario);
    }
    
    public Iterable<Convite> findByRemetente(String remetente){
        return repository.findByRemetente(remetente);
    }
    
    public void deleteConvite(Convite convite){
        repository.delete(convite);
    }
}
