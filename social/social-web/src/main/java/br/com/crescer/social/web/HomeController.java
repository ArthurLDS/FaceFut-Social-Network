/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.web;

import br.com.crescer.social.entity.Amigo;
import br.com.crescer.social.entity.Post;
import br.com.crescer.social.entity.Time;
import br.com.crescer.social.entity.Usuario;
import br.com.crescer.social.service.Service.AmigoService;
import org.springframework.security.core.userdetails.User;
import br.com.crescer.social.service.Service.PostService;
import br.com.crescer.social.service.Service.TimeService;
import br.com.crescer.social.service.Service.UsuarioService;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
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
    
    @Autowired
    AmigoService amigoService;
    
    @Autowired
    TimeService timeService;

    @RequestMapping(value = "/home")
    String home(Model model) {
        
        Post post = new Post();
        Amigo amigo = new Amigo();
                
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioService.findByEmail(user.getUsername());
        
        Iterable<Amigo> amigos = amigoService.listAll();
        model.addAttribute("amigos", amigos);
        model.addAttribute("amigo", amigo);
        model.addAttribute("usuario", usuario);
        model.addAttribute("post", post);
        
        List<Post> posts = service.findAllByOrderByIdDesc();
        
        model.addAttribute("posts", posts);

        return "home";
    }

    @RequestMapping(value = "/postar", method = RequestMethod.POST)
    public String save(@ModelAttribute Post post) {
        
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        post.setAutor(user.getUsername());
        post.setData(new Date());
        
        service.save(post);
        return "redirect:home";
    }
    
    @RequestMapping(value = "/pesquisar", method = RequestMethod.POST)
    public String pesquisar(Model model, @ModelAttribute Amigo amigo){
        String nome = amigo.getNome();
        Iterable<Amigo> amigosFiltrados = amigoService.findByNome(nome);
        model.addAttribute("amigosFiltrados", amigosFiltrados);
        return "redirect:home";
    }
    
}
