/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.web;

import br.com.crescer.social.entity.Post;
import br.com.crescer.social.service.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author arthur.souza
 */
@Controller
public class HomeController {
    
    @Autowired
    PostService service;
    
    @RequestMapping(value="/home")
    String home(Model model){
        
        Post post = new Post();
        model.addAttribute("post", post);
        Iterable<Post> posts  =  service.findAll();
        model.addAttribute("posts", posts);
        
        return "home";
    }
}
