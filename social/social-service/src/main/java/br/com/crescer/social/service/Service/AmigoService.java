/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service.Service;

import br.com.crescer.social.entity.Amigo;
import br.com.crescer.social.entity.Convite;
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

    @Autowired
    private UsuarioService usuarioService;

    public Iterable<Amigo> listAll() {
        return repository.findAll();
    }

    public Amigo findById(Long id) {
        return repository.findById(id);
    }

    public Iterable<Amigo> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Amigo findFirstByEmail(String email) {
        List<Amigo> lista = (List) repository.findByEmail(email);
        return lista.get(0);
    }

    public Iterable<Amigo> findByNome(String nome) {
        return repository.findByNome(nome);
    }

    public Iterable<Amigo> findByNomeIgnoreCaseContaining(String nome) {
        return repository.findByNomeIgnoreCaseContaining(nome);
    }

    public void save(Amigo amigo) {
        repository.save(amigo);
    }

    //Método para fazer Parse de Usuario para Amigo
    public void save(Usuario usuario) {
        Amigo amigo = new Amigo();

        amigo.setNome(usuario.getNome());
        amigo.setEmail(usuario.getEmail());
        amigo.setTime(usuario.getTime());
        amigo.setPerfil(usuario.getPerfil());

        repository.save(amigo);
    }

    public void adicionarAmigo(Usuario usuario, Amigo amigo, Convite convite, String extremidade) {
        //Adiciona o amigo no usuario
        List<Amigo> amigos = usuario.getAmigos();
        amigos.add(amigo);
        usuario.setAmigos(amigos);

        //Remove convite
        if (extremidade.equals("DESTINATARIO")) {
            List<Convite> convites = usuario.getConvitesRecebidos();
            convites.remove(convite);//Pode remover isso.
            usuario.setConvitesRecebidos(convites);
            
        } else if (extremidade.equals("REMETENTE")) {
            List<Convite> convites = usuario.getConvitesEnviados();
            convites.remove(convite);//Pode remover isso.
            usuario.setConvitesEnviados(convites);
        }
        usuarioService.save(usuario);
    }
    
    public void removerAmigo(Usuario usuario, Amigo exAmigo){
        List<Amigo> amigosDoUsuario = usuario.getAmigos();
        amigosDoUsuario.remove(exAmigo);
        usuario.setAmigos(amigosDoUsuario);
        
        usuarioService.save(usuario);
    }

}
