/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service.Service;

import br.com.crescer.social.entity.Amigo;
import br.com.crescer.social.entity.Convite;
import br.com.crescer.social.entity.Perfil;
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

    public List<Amigo> listAll() {
        return (List) repository.findAll();
    }

    public Amigo findById(Long id) {
        return repository.findById(id);
    }

    public Amigo findByPerfil(Perfil perfil) {
        return repository.findByPerfil(perfil);
    }
    
    public void save(Amigo amigo) {
        repository.save(amigo);
    }

    //Método para fazer Parse de Usuario para Amigo
    public void save(Usuario usuario) {
        Amigo amigo = new Amigo(usuario.getPerfil());
        repository.save(amigo);
    }
    
    public void delete(Amigo amigo){
        repository.delete(amigo);
    }

    public Amigo filtrarAmigo(Convite convite, String extremidade) {
        
        Amigo amigo = null;
        switch (extremidade) {
            case "REMETENTE":
                if (findByPerfil(convite.getPerfilRemetente()) == null) {
                    amigo = new Amigo(convite.getPerfilRemetente());
                    save(amigo);
                } else {
                    amigo = findByPerfil(convite.getPerfilRemetente());
                }   break;
            case "DESTINATARIO":
                if(findByPerfil(convite.getPerfilDestinatario()) == null){
                    amigo = new Amigo(convite.getPerfilDestinatario());
                    save(amigo);
                }
                else{
                    amigo = findByPerfil(convite.getPerfilDestinatario());
            }   break;  
        }
        return amigo;
    }

}
