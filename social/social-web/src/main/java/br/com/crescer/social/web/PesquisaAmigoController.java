/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.web;

import br.com.crescer.social.entity.entities.Amigo;
import br.com.crescer.social.entity.entities.Convite;
import br.com.crescer.social.entity.entities.Perfil;
import br.com.crescer.social.entity.entities.Usuario;
import br.com.crescer.social.service.Service.AmigoService;
import br.com.crescer.social.service.Service.UsuarioService;
import br.com.crescer.social.service.Service.PerfilService;     
import java.util.ArrayList;
import org.springframework.security.core.userdetails.User;
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
@RequestMapping(value = "pesquisa")
public class PesquisaAmigoController {

    @Autowired
    AmigoService amigoService;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    PerfilService perfilService;
    
    @RequestMapping(value = "pesquisarAmigo", method = RequestMethod.GET)
    public String pesquisarAmigo(Model model, String filtro) {
        User userSessao = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioService.findByEmail(userSessao.getUsername());
        
        List<Perfil> perfis = (List)perfilService.findByNomeIgnoreCaseContaining(filtro);
        
        model.addAttribute("perfis",perfis.stream()
                                    .filter(p -> !p.getEmail().equals(usuario.getEmail()))
                                    .collect(Collectors.toList()));
        
        model.addAttribute("perfisAmigosUsuario", usuario.getAmigos().stream()
                                    .map(a -> a.getPerfil())
                                    .collect(Collectors.toList()));
        
        List<String> convitesEnviados = usuario.getConvitesEnviados().stream()
                                    .map(c -> c.getPerfilDestinatario().getEmail())
                                    .collect(Collectors.toList());
        model.addAttribute("convitesEnviados", convitesEnviados);
        
        return "partialPesquisarAmigos";
    }
}
