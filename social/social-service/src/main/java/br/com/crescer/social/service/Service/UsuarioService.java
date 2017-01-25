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
import br.com.crescer.social.service.Repository.UsuarioRepository;
import java.util.List;
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

    public Iterable<Usuario> listAll() {
        return repository.findAll();
    }
    
    public Usuario findOne(Long id){
        return repository.findOne(id);
    }
    
    public Usuario findByPerfil(Perfil perfil) {
        return repository.findByPerfil(perfil);
    }

    public Usuario findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public void save(Usuario usuario) {
        repository.save(usuario);
    }

    public void removerAmigo(Usuario usuario, Amigo exAmigo) {
        List<Amigo> amigosDoUsuario = usuario.getAmigos();
        amigosDoUsuario.remove(exAmigo);
        usuario.setAmigos(amigosDoUsuario);

        save(usuario);
    }

    public void adicionarAmigo(Usuario usuario, Amigo amigo, Convite convite, String extremidade) {

        List<Amigo> amigos = usuario.getAmigos();
        amigos.add(amigo);
        usuario.setAmigos(amigos);

        //Remove convite
        switch (extremidade) {
            case "DESTINATARIO": {
                List<Convite> convites = usuario.getConvitesRecebidos();
                convites.remove(convite);//Pode remover isso.
                usuario.setConvitesRecebidos(convites);
                break;
            }
            case "REMETENTE": {
                List<Convite> convites = usuario.getConvitesEnviados();
                convites.remove(convite);//Pode remover isso.
                usuario.setConvitesEnviados(convites);
                break;
            }
        }
        save(usuario);
    }
}
