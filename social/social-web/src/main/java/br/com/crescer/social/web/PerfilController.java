/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.web;

import br.com.crescer.social.entity.entities.Perfil;
import br.com.crescer.social.entity.entities.Post;
import br.com.crescer.social.entity.entities.Usuario;
import br.com.crescer.social.service.Service.PerfilService;
import br.com.crescer.social.service.Service.PostService;
import br.com.crescer.social.service.Service.UsuarioService;
import java.util.List;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String carrgarView(Model model, @RequestParam(required = true) Long id) {

        Usuario usuario = usuarioService.findByPerfil(perfilService.findById(id));
        Usuario usuarioLogado = getUserSessao();

        model.addAttribute("usuario", usuario);
        model.addAttribute("usuarioLogado", usuarioLogado);
        return "perfil";
    }

    @RequestMapping(value = "/carregarBtnAmizade", method = RequestMethod.GET)
    public String carregarBtnAmizade(Model model, Long id) {
        Usuario usuario = usuarioService.findByPerfil(perfilService.findById(id));
        Usuario usuarioLogado = getUserSessao();

        model.addAttribute("convitesEnviados", usuarioLogado.getConvitesEnviados().stream()
                .map(c -> c.getPerfilDestinatario())
                .collect(Collectors.toList()));
        model.addAttribute("perfisAmigosUsuario", usuarioLogado.getAmigos().stream()
                .map(a -> a.getPerfil())
                .collect(Collectors.toList()));
        model.addAttribute("usuario", usuario);

        return "partialBtnsAmizade";
    }

    private Usuario getUserSessao() {
        User userSpringSecurity = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return usuarioService.findByEmail(userSpringSecurity.getUsername());
    }
}
