/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.web;

import br.com.crescer.social.entity.Amigo;
import br.com.crescer.social.entity.Post;
import br.com.crescer.social.entity.Usuario;
import br.com.crescer.social.service.Service.AmigoService;
import br.com.crescer.social.service.Service.PostService;
import br.com.crescer.social.service.Service.UsuarioService;
import java.util.ArrayList;
import org.springframework.security.core.userdetails.User;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Arthur
 */
@Controller
@RequestMapping(value="postagem")
public class PostController {
    
    @Autowired
    PostService postService;
    @Autowired
    AmigoService amigoService;
    @Autowired
    UsuarioService usuarioService;
    
    @RequestMapping(value="carregarPosts", method = RequestMethod.GET)
    public String carregarPosts(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuarioLogado = usuarioService.findByEmail(user.getUsername());
        
        List<Post> posts = postService.findAllByOrderByIdDesc();
        List<Post> postsFiltrados = filtrarPosts(usuarioLogado.getAmigos(), posts, usuarioLogado);

        model.addAttribute("posts", postsFiltrados);
        return "partialPostagem";
    }
    
    private List<Post> filtrarPosts(List<Amigo> amigos, List<Post> posts, Usuario usuario) {
        
        List<String> nomeAmigos = new ArrayList<>();
        amigos.stream().forEach((amigo) -> {
            nomeAmigos.add(amigo.getEmail());
        });
        List<Post> postsFiltrados = new ArrayList<>();

        for (int i = 0; i < posts.size(); i++) {
            for (int j = 0; j < nomeAmigos.size(); j++) {
                if (posts.get(i).getAutor().equals(nomeAmigos.get(j))) {
                    postsFiltrados.add(posts.get(i));
                }
            }
            if (posts.get(i).getAutor().equals(usuario.getEmail())) {
                postsFiltrados.add(posts.get(i));
            }
        }
        return postsFiltrados;
    }
}
