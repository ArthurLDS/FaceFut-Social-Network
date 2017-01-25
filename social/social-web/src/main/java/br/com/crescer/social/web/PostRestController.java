/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.web;

import br.com.crescer.social.entity.entities.Post;
import br.com.crescer.social.entity.entities.Usuario;
import br.com.crescer.social.service.Service.PostService;
import br.com.crescer.social.service.Service.UsuarioService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Arthur
 */
@RestController
@RequestMapping(value="postagemRest")
public class PostRestController {
    
    @Autowired
    PostService postService;
    
    @Autowired
    UsuarioService usuarioService;
    
    @RequestMapping(value="postar", method = RequestMethod.POST)
    public void postar(Model model, String texto){
        
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuarioLogado = usuarioService.findByEmail(user.getUsername());
        
        Post post = new Post(texto, new Date(), usuarioLogado.getPerfil());
        postService.save(post);
        
        List<Post> postsUsuario = usuarioLogado.getPosts();
        postsUsuario.add(post);
        usuarioLogado.setPosts(postsUsuario);
        
        usuarioService.save(usuarioLogado);
    }
}
