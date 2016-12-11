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
    String home(Model model, @RequestParam(required = false) Long id) {

        if (id != null) {
            Amigo amigoParaAdc = amigoService.findById(id);
            User userRemetente = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Convite convite = new Convite();

            convite.setDestinatario(amigoParaAdc.getEmail());
            convite.setRemetente(userRemetente.getUsername());
            convite.setData(new Date());

            conviteService.save(convite);
        }

        Post post = new Post();
        Amigo amigo = new Amigo();

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioService.findByEmail(user.getUsername());

        //model.addAttribute("amigos", amigoService.listAll());
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
    public String pesquisar(Model model, @ModelAttribute Amigo amigao, RedirectAttributes redirectAttributes) {

        String nome = amigao.getNome();
        Iterable<Amigo> amigos = amigoService.findByNome(nome);

        // Mandando numero de dados encontrados para a tela.
        List<Amigo> lista = (List) amigos;

        int quantidade = lista.size();
        if (quantidade != 0) {
            redirectAttributes.addFlashAttribute("msg", quantidade);
        } else {
            redirectAttributes.addFlashAttribute("acheiNada", "0");
        }

        redirectAttributes.addFlashAttribute("amigos", amigos);
        return "redirect:home";
    }

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public String listAll(RedirectAttributes redirectAttributes) {

        Iterable<Amigo> amigos = amigoService.listAll();
        redirectAttributes.addFlashAttribute("amigos", amigos);
        return "redirect:home";
    }

    @RequestMapping(value = "/adicionar/{email}", method = RequestMethod.GET)
    public String adicionarAmigo(@PathVariable String email) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Convite convite = new Convite();

        convite.setDestinatario(email);
        convite.setRemetente(user.getUsername());
        convite.setData(new Date());

        conviteService.save(convite);
        return "home";
    }
}
