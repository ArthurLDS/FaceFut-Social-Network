/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.web;

import br.com.crescer.social.entity.entities.Perfil;
import br.com.crescer.social.entity.entities.Usuario;
import br.com.crescer.social.service.Service.AmigoService;
import br.com.crescer.social.service.Service.PerfilService;
import br.com.crescer.social.service.Service.UsuarioService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Arthur
 */
@RestController
@RequestMapping(value = "pesquisaRest")
public class PesquisaAmigoRestController {

    @Autowired
    AmigoService amigoService;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    PerfilService perfilService;

    @RequestMapping(value = "pesquisarAmigo", method = RequestMethod.GET)
    public List<Perfil> pesquisarAmigo(Model model, String filtro) {
        User userSessao = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioService.findByEmail(userSessao.getUsername());

        List<Perfil> perfis = (List) perfilService.findByNomeIgnoreCaseContaining(filtro);
        
        model.addAttribute("perfisAmigosUsuario", usuario.getAmigos().stream()
                .map(a -> a.getPerfil())
                .collect(Collectors.toList()));

        List<String> convitesEnviados = usuario.getConvitesEnviados().stream()
                .map(c -> c.getPerfilDestinatario().getEmail())
                .collect(Collectors.toList());
        model.addAttribute("convitesEnviados", convitesEnviados);

        return perfis.stream()
                .filter(p -> !p.getEmail().equals(usuario.getEmail()))
                .collect(Collectors.toList());
    }
    
    @RequestMapping(value="getUsuariorConvitesEnviados", method = RequestMethod.GET)
    public List<Perfil> getConvitesEnviados(Long id){
        Usuario usuario = usuarioService.findOne(id);
        
        List<Perfil> perfilConvitesEnviados = usuario.getConvitesEnviados().stream()
                .map(c -> c.getPerfilDestinatario())
                .collect(Collectors.toList());
        return perfilConvitesEnviados;
    }
    
    @RequestMapping(value="getAmigosUsuario", method = RequestMethod.GET)
    public List<Perfil> getAmigosUsuario(Long id){
        Usuario usuario = usuarioService.findOne(id);
        
        return usuario.getAmigos().stream()
                .map(a -> a.getPerfil())
                .collect(Collectors.toList());
    }
}
