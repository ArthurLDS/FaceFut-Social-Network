/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service.Service;

import br.com.crescer.social.entity.Usuario;
import br.com.crescer.social.service.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Arthur
 */
@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository repository;
    
    public Iterable<Usuario> listAll(){
        return repository.findAll();
    }
    
    public Usuario findByEmail(String email){
        return repository.findByEmail(email);
    }
    
    public void save(Usuario usuario){
        repository.save(usuario);
    }
}
