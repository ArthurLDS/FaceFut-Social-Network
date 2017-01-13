/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.web;

import br.com.crescer.social.entity.Amigo;
import br.com.crescer.social.entity.Convite;
import br.com.crescer.social.entity.Usuario;
import br.com.crescer.social.service.Service.AmigoService;
import br.com.crescer.social.service.Service.ConviteService;
import br.com.crescer.social.service.Service.UsuarioService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Arthur
 */
@RestController
@RequestMapping(value="/amigo")
public class AmigoRestController {
    
    @Autowired
    AmigoService amigoService;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    ConviteService conviteService;
    
    @RequestMapping(value="/enviarConvite", method = RequestMethod.GET) // Colocar PUT Depois
    public void enviarConvite(Long id){
        
        User userSessao = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Amigo amigoDestinatario = amigoService.findById(id);
        
        //Formatando convite
        /*Convite convite = new Convite();
        convite.setRemetente(userSessao.getUsername());
        convite.setDestinatario(amigoDestinatario.getEmail());
        convite.setData(new Date());*/
        
        Convite convite = new Convite(userSessao.getUsername(), amigoDestinatario.getEmail(), new Date());
        conviteService.save(convite);
        
        Usuario usuarioAtual = usuarioService.findByEmail(userSessao.getUsername());
        
        List<Convite> convitesUsuario = usuarioAtual.getConvites();
        convitesUsuario.add(convite);
        usuarioAtual.setConvites(convitesUsuario);
        usuarioService.save(usuarioAtual);
        
    }
}
