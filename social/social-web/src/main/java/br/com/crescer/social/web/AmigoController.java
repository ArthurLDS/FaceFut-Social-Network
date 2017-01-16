/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.web;

import br.com.crescer.social.entity.Convite;
import br.com.crescer.social.entity.Usuario;
import br.com.crescer.social.service.Service.AmigoService;
import br.com.crescer.social.service.Service.ConviteService;
import br.com.crescer.social.service.Service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Arthur
 */
@Controller
@RequestMapping(value="/amigo")
public class AmigoController {
    
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    ConviteService conviteService;
    
    @RequestMapping(value="/carregarListaAmigos", method =  RequestMethod.GET)
    public String carregarListaAmigos(Model model){
        
        User userSessao = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioService.findByEmail(userSessao.getUsername());
        
        model.addAttribute("amigos", usuario.getAmigos());
        return "partialListaAmigos";
    } 
    
    @RequestMapping(value="carregarConvites", method = RequestMethod.GET)
    public String carregarConvites(Model model){
        Usuario usuario = usuarioService.findByEmail(getUserSessao().getUsername());
        Iterable<Convite> convites = usuario.getConvites();
        
        model.addAttribute("convites", convites);
        return "partialListagemConvites";
    }
    
    private User getUserSessao(){
        return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
