/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.web;

import br.com.crescer.social.entity.entities.Amigo;
import br.com.crescer.social.entity.entities.Post;
import br.com.crescer.social.entity.entities.Usuario;
import br.com.crescer.social.service.Service.AmigoService;
import br.com.crescer.social.service.Service.PostService;
import br.com.crescer.social.service.Service.UsuarioService;
import java.util.ArrayList;
import org.springframework.security.core.userdetails.User;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
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
@RequestMapping(value = "postagem")
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    AmigoService amigoService;
    @Autowired
    UsuarioService usuarioService;

    @RequestMapping(value = "carregarPosts", method = RequestMethod.GET)
    public String carregarPosts(Model model, Long id, String arquivo) {
        Usuario usuario = usuarioService.findOne(id);

        String view = "";
        List<Post> posts = new ArrayList<>();
        
        if (arquivo.equals("HOME")) {
            posts = postService.filtrarPosts(postService.findAllByOrderByIdDesc(), usuario);
            view = "partialPostagem";
            
        } else if (arquivo.equals("PERFIL")) {
            posts = posts = usuario.getPosts().stream()
                    .sorted((p1, p2) -> p2.getId().compareTo(p1.getId()))
                    .collect(Collectors.toList());
            view = "partialPostagemPerfil";
            
        }

        model.addAttribute("posts", posts);
        return view;
    }

    @RequestMapping(value = "carregarBtnUploadImagem", method = RequestMethod.GET)
    public String carregarBtnUploadImagem(Model model) {
        return "partialBtnUploadImagemPost";
    }
    
}
