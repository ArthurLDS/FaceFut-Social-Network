/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service.Service;

import br.com.crescer.social.entity.entities.Convite;
import br.com.crescer.social.entity.entities.Perfil;
import br.com.crescer.social.entity.entities.Usuario;
import br.com.crescer.social.service.Repository.ConviteRepository;
import java.util.List;
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
    
    @Autowired
    UsuarioService usuarioService;
    
    public Iterable<Convite> findAll(){
        return repository.findAll();
    }
    
    public void save(Convite convite){
        repository.save(convite);
    }
    
    public Iterable<Convite> findByPerfilDestinatario(Perfil destinatario){
        return repository.findByPerfilDestinatario(destinatario);
    }
    
    public Iterable<Convite> findByPerfilRemetente(Perfil remetente){
        return repository.findByPerfilRemetente(remetente);
    }
    
    public Convite findById(Long id){
        return repository.findById(id);
    }
    
    public void deleteConvite(Convite convite){
        repository.delete(convite);
    }
    
    public void adicionarConviteUsuario(Usuario usuario, Convite convite, String extremidade) {

        if (extremidade.equals("REMETENTE")) {
            List<Convite> convites = usuario.getConvitesEnviados();
            convites.add(convite);
            usuario.setConvitesEnviados(convites);    
        }
        else if(extremidade.equals("DESTINATARIO")){
            List<Convite> convites = usuario.getConvitesRecebidos();
            convites.add(convite);
            usuario.setConvitesRecebidos(convites);
        }
        usuarioService.save(usuario);
    }
}
