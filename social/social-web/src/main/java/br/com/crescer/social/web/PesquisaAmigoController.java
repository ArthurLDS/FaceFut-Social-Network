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
        
        Iterable<Amigo> amigos = filtrarListaDeAmigos((List) amigoService.findByNomeIgnoreCaseContaining(filtro), usuario);
        model.addAttribute("amigos", amigos);
        
        List<String> destinatarios = usuario.getConvites().stream().map(c -> c.getDestinatario()).collect(Collectors.toList());
        model.addAttribute("convites", destinatarios);
        return "partialPesquisarAmigos";
    }
    
    // Refatorar isto.
    private List<Amigo> filtrarListaDeAmigos(List<Amigo> amigos, Usuario usuario) {
        List<String> nomeAmigos = new ArrayList<>();
        List<Amigo> amigosDoUsuario = usuario.getAmigos();
        List<Amigo> amigosFiltrados = amigos;
        
        
        for (int i = 0; i < amigosDoUsuario.size(); i++) {
            nomeAmigos.add(amigosDoUsuario.get(i).getEmail());
        }

        for (int i = 0; i < amigos.size(); i++) {
            Amigo amigoAtual = amigos.get(i);
            if (amigosDoUsuario.size() > 0) {
                for (int j = 0; j < nomeAmigos.size(); j++) {
                    if (amigoAtual.getEmail().equals(nomeAmigos.get(j))) {
                        amigosFiltrados.remove(amigoAtual);
                    }
                }
            }
        }
        for (int i = 0; i < amigosFiltrados.size(); i++) {
            Amigo amigoAtual = amigos.get(i);
            if (amigoAtual.getEmail().equals(usuario.getEmail())) {
                amigosFiltrados.remove(amigoAtual);
            }
        }
        
        
        return amigosFiltrados;
                
    }
}
