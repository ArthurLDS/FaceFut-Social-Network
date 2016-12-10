/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service.Service;

import br.com.crescer.social.entity.Amigo;
import br.com.crescer.social.entity.Usuario;
import br.com.crescer.social.service.Repository.AmigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Arthur
 */
@Service
public class AmigoService {
    
    @Autowired
    private AmigoRepository repository;
    
    public Iterable<Amigo> listAll(){
        return repository.findAll();
    }
    
    public Iterable<Amigo> findByEmail(String email){
        return repository.findByEmail(email);
    }
    
    public Iterable<Amigo> findByNome(String nome){
        return repository.findByNome(nome);
    }
    
    public void save(Amigo amigo){
        repository.save(amigo);
    }
    
    //Método para fazer Parse de Usuario para Amigo
    public void save(Usuario usuario){
        Amigo amigo = new Amigo();
        
        amigo.setNome(usuario.getNome());
        amigo.setEmail(usuario.getEmail());
        amigo.setTime(usuario.getTime());
        
        repository.save(amigo);
    }
    
}
