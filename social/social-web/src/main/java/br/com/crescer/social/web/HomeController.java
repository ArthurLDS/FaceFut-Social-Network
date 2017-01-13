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
import br.com.crescer.social.entity.Convite;
import br.com.crescer.social.service.Service.AmigoService;
import org.springframework.security.core.userdetails.User;
import br.com.crescer.social.service.Service.PostService;
import br.com.crescer.social.service.Service.TimeService;
import br.com.crescer.social.service.Service.UsuarioService;
import br.com.crescer.social.service.Service.ConviteService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @Autowired
    ConviteService conviteService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    String home(Model model, @RequestParam(required = false) Long idaprova, @RequestParam(required = false) Long idreprova) {
        User userAtual = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (idreprova != null) {
            Convite conviteReprovado = conviteService.findById(idreprova);
            conviteService.deleteConvite(conviteReprovado);
        }
        if (idaprova != null) {
            //Aceitando e adicionando amigo no Usuario atual
            Convite conviteAprovado = conviteService.findById(idaprova);

            Amigo amigoRemetente = amigoService.findFirstByEmail(conviteAprovado.getRemetente());
            Usuario usuarioEntity = usuarioService.findByEmail(userAtual.getUsername());
            List<Amigo> amigosDoUsuario = usuarioEntity.getAmigos();
            amigosDoUsuario.add(amigoRemetente);
            usuarioEntity.setAmigos(amigosDoUsuario);

            usuarioService.save(usuarioEntity);

            //Adicionando amigo no Usuario que enviou o convite..
            Amigo amigoDestinatario = amigoService.findFirstByEmail(conviteAprovado.getDestinatario());
            Usuario usuarioRemetente = usuarioService.findByEmail(conviteAprovado.getRemetente());
            List<Amigo> amigosDoRemetente = usuarioRemetente.getAmigos();
            amigosDoRemetente.add(amigoDestinatario);
            usuarioRemetente.setAmigos(amigosDoRemetente);

            usuarioService.save(usuarioRemetente);

            //Agora excluimos o convite
            conviteService.deleteConvite(conviteAprovado);
        }

        //Busca os convites que enviaram para ele
        Iterable<Convite> convites = conviteService.findByDestinatario(userAtual.getUsername());
        usuarioService.findByEmail(userAtual.getUsername()).setConvites((List) convites);

        Amigo amigo = new Amigo();
        Usuario usuario = usuarioService.findByEmail(userAtual.getUsername());

        //model.addAttribute("amigos", amigoService.listAll());
        model.addAttribute("convites", convites);
        model.addAttribute("amigo", amigo);
        model.addAttribute("usuario", usuario);

        return "home";
    }
}
