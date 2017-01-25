/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.web;

import br.com.crescer.social.entity.Perfil;
import br.com.crescer.social.entity.Post;
import br.com.crescer.social.entity.Usuario;
import br.com.crescer.social.service.Service.PerfilService;
import br.com.crescer.social.service.Service.PostService;
import br.com.crescer.social.service.Service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Arthur
 */
@Controller
public class PerfilController {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    PerfilService perfilService;
    @Autowired
    PostService postService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String carrgarView(Model model) {
        Usuario usuario = usuarioService.findOne(1000l);
        Perfil perfil = perfilService.findById(1000l);
        List<Post> posts = usuario.getPosts();
        
        model.addAttribute("posts", posts);
        model.addAttribute("perfil", perfil);
        return "perfil";
    }
}
