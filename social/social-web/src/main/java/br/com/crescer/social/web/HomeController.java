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
    String home(Model model, @RequestParam(required = false) Long id, @RequestParam(required = false) Long idaprova, @RequestParam(required = false) Long idreprova) {
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

        //Envia convites.
        if (id != null) {
            Amigo amigoParaAdc = amigoService.findById(id);
            Convite convite = new Convite();
            convite.setDestinatario(amigoParaAdc.getEmail());
            convite.setRemetente(userAtual.getUsername());
            convite.setData(new Date());

            conviteService.save(convite);
        }
        Post post = new Post();
        Amigo amigo = new Amigo();

        Usuario usuario = usuarioService.findByEmail(userAtual.getUsername());

        //model.addAttribute("amigos", amigoService.listAll());
        List<Amigo> amigos = usuario.getAmigos();
        model.addAttribute("friends", amigos);
        model.addAttribute("numAmigos", usuario.getAmigos().size());
        model.addAttribute("convites", convites);
        model.addAttribute("amigo", amigo);
        model.addAttribute("usuario", usuario);
        model.addAttribute("post", post);

        List<Post> posts = service.findAllByOrderByIdDesc();
        List<Post> postsFiltrados = filtrarPosts(amigos, posts, usuario);

        model.addAttribute("posts", postsFiltrados);
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

        User userSessao = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioService.findByEmail(userSessao.getUsername());

        String nome = amigao.getNome();
        Iterable<Amigo> amigos = filtrarListaDeAmigos((List) amigoService.findByNome(nome), usuario);

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

        User userSessao = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioService.findByEmail(userSessao.getUsername());

        Iterable<Amigo> amigos = amigoService.listAll();
        List<Amigo> listaAmigos = filtrarListaDeAmigos((List) amigos, usuario);
        redirectAttributes.addFlashAttribute("amigos", listaAmigos);
        return "redirect:home";
    }

    private List<Post> filtrarPosts(List<Amigo> amigos, List<Post> posts, Usuario usuario) {
        List<String> nomeAmigos = new ArrayList<>();

        for (int i = 0; i < amigos.size(); i++) {
            nomeAmigos.add(amigos.get(i).getEmail());
        }
        List<Post> postsFiltrados = new ArrayList<>();

        for (int i = 0; i < posts.size(); i++) {
            for (int j = 0; j < nomeAmigos.size(); j++) {
                if (posts.get(i).getAutor().equals(nomeAmigos.get(j))) {
                    postsFiltrados.add(posts.get(i));
                }
            }
            if (posts.get(i).getAutor().equals(usuario.getEmail())) {
                postsFiltrados.add(posts.get(i));
            }
        }
        return postsFiltrados;
    }

    private List<Amigo> filtrarListaDeAmigos(List<Amigo> amigos, Usuario usuario) {
        List<String> nomeAmigos = new ArrayList<>();
        List<Amigo> amigosDoUsuario = usuario.getAmigos();
        List<Amigo> amigosFiltrados = new ArrayList<>();

        for (int i = 0; i < amigosDoUsuario.size(); i++) {
            nomeAmigos.add(amigosDoUsuario.get(i).getEmail());
        }

        for (int i = 0; i < amigos.size(); i++) {
            Amigo amigoAtual = amigos.get(i);
            if (amigosDoUsuario.size() > 0) {
                for (int j = 0; j < nomeAmigos.size(); j++) {
                    if (!amigoAtual.getEmail().equals(nomeAmigos.get(j)) && !amigoAtual.getEmail().equals(usuario.getEmail())) {
                        amigosFiltrados.add(amigos.get(i));
                    }
                }
            }
            else if(amigos.get(i).getEmail().equals(usuario.getEmail())){
                amigos.remove(i);
                amigosFiltrados = amigos;
            }
        }
        
        return amigosFiltrados;
    }
}
