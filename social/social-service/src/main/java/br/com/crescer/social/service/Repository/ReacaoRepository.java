/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service.Repository;

import br.com.crescer.social.entity.entities.Reacao;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Arthur
 */
@Repository
public interface ReacaoRepository extends CrudRepository<Reacao, Long>{
    
    public List<Reacao> findByPerfisCurtidas(Reacao reacao);
}
