/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service.Service;

import br.com.crescer.social.entity.Amigo;
import br.com.crescer.social.entity.Usuario;
import br.com.crescer.social.service.Repository.AmigoRepository;
import java.util.List;
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
    
    public Amigo findById(Long id){
        return repository.findById(id);
    }
    
    public Iterable<Amigo> findByEmail(String email){
        return repository.findByEmail(email);
    }
    
    public Amigo findFirstByEmail(String email){
        List<Amigo> lista = (List)repository.findByEmail(email);
        return lista.get(0);
    }
    
    public Iterable<Amigo> findByNome(String nome){
        return repository.findByNome(nome);
    }
    
    public Iterable<Amigo> findByNomeIgnoreCaseContaining(String nome){
        return repository.findByNomeIgnoreCaseContaining(nome);
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
        amigo.setPerfil(usuario.getPerfil());
        
        repository.save(amigo);
    }
    
}
