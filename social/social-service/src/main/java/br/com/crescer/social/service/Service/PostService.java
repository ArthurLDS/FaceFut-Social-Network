/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service.Service;

import br.com.crescer.social.entity.entities.Amigo;
import br.com.crescer.social.entity.entities.Perfil;
import br.com.crescer.social.entity.entities.Post;
import br.com.crescer.social.entity.entities.Usuario;
import br.com.crescer.social.service.Repository.PostRepository;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

/**
 *
 * @author arthur.souza
 */
@Service
public class PostService {

    @Autowired
    PostRepository repository;
    
    public Post findOne(Long id){
        return repository.findOne(id);
    }
    
    public Iterable<Post> findAll() {
        return repository.findAll();
    }

    public void save(Post post) {
        repository.save(post);
    }
    
    public Page<Post> findByPerfisAutor(List<Perfil> perfis, Pageable p){
        return repository.findByPerfilAutorIn(perfis, p);
    }
    
    public List<Post> findAllByOrderByIdDesc() {
        return repository.findAllByOrderByIdDesc();
    }
    
    public Page<Post> findAllByOrderByIdDesc(Pageable p){
        return repository.findAllByOrderByIdDesc(p);
    }

    public List<Post> filtrarPosts(List<Post> posts, Usuario usuario) {

        List<String> emailAmigos = usuario.getAmigos().stream()
                .map(a -> a.getPerfil().getEmail())
                .collect(Collectors.toList());

        return posts.stream()
                .filter(p -> emailAmigos.contains(p.getPerfilAutor().getEmail())
                        || p.getPerfilAutor().getEmail().equals(usuario.getEmail()))
                .collect(Collectors.toList());
    }
    
    /*public Page<Post> filtrarPosts(List<Post> posts, Usuario usuario, Pageable pageable) {
        
        List<String> emailAmigos = usuario.getAmigos().stream()
                .map(a -> a.getPerfil().getEmail())
                .collect(Collectors.toList());
        
        List<Post> postsList = posts.stream()
                .filter(p -> emailAmigos.contains(p.getPerfilAutor().getEmail())
                        || p.getPerfilAutor().getEmail().equals(usuario.getEmail()))
                .collect(Collectors.toList());
        
        return new PageImpl<>(postsList, pageable, postsList.size());
    }*/

    public Post salvar(String caminhoImagem, String texto, Usuario usuario) {

        if (!caminhoImagem.isEmpty() && caminhoImagem != null) {
            return new Post(texto, "/imgs/post/" + caminhoImagem, new Date(), usuario.getPerfil());
        }
        return new Post(texto, new Date(), usuario.getPerfil());

    }
    
    public Page<Post> findAllByPerfilAutor(Perfil perfil, Pageable pageable){
        return repository.findAllByPerfilAutor(perfil, pageable);
    }
}
