/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.web;

import br.com.crescer.social.entity.Post;
import br.com.crescer.social.entity.Usuario;
import org.springframework.security.core.userdetails.User;
import br.com.crescer.social.service.Service.PostService;
import br.com.crescer.social.service.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author arthur.souza
 */
@Controller
public class HomeController {

    @Autowired
    PostService service;

    @Autowired
    UsuarioService usuarioService;

    @RequestMapping(value = "/home")
    String home(Model model) {
        Post post = new Post();

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioService.findByEmail(user.getUsername());

        model.addAttribute("usuario", usuario);
        model.addAttribute("post", post);
        Iterable<Post> posts = service.findAll();
        model.addAttribute("posts", posts);

        return "home";
    }

    @RequestMapping(value = "/postar", method = RequestMethod.POST)
    public String save(@ModelAttribute Post post) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setAutor(user.getUsername());
        service.save(post);
        return "redirect:home";
    }
}
