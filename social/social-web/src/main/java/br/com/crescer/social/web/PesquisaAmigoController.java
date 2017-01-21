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
import br.com.crescer.social.service.Service.UsuarioService;
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

    @RequestMapping(value = "pesquisarAmigo", method = RequestMethod.GET)
    public String pesquisarAmigo(Model model, String filtro) {
        User userSessao = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioService.findByEmail(userSessao.getUsername());
        
        List<Amigo> amigos = (List)amigoService.findByNomeIgnoreCaseContaining(filtro);
        
        model.addAttribute("amigos",amigos.stream()
                                    .filter(a -> !a.getEmail().equals(usuario.getEmail()))
                                    .collect(Collectors.toList()));
        
        model.addAttribute("amigosUsuario", usuario.getAmigos());
        
        List<String> convitesEnviados = usuario.getConvitesEnviados().stream()
                .map(c -> c.getPerfilDestinatario().getEmail())
                .collect(Collectors.toList());
        
        model.addAttribute("convitesEnviados", convitesEnviados);
        return "partialPesquisarAmigos";
    }
}
