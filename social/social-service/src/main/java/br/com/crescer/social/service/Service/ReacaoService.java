/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service.Service;

import br.com.crescer.social.entity.entities.Reacao;
import br.com.crescer.social.service.Repository.ReacaoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Arthur
 */
@Service
public class ReacaoService {
    
    @Autowired 
    ReacaoRepository reacaoRepository;
    
    public Reacao findOne(Long id){
        return reacaoRepository.findOne(id);
    }
    
    public void save(Reacao reacao){
        reacaoRepository.save(reacao);
    }
    
    public List<Reacao> findByPerfil(Reacao reacao){
        return reacaoRepository.findByPerfisCurtidas(reacao);
    }
    
}
