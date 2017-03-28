/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.web;

import br.com.crescer.social.entity.entities.Amigo;
import br.com.crescer.social.entity.entities.Perfil;
import br.com.crescer.social.entity.entities.Post;
import br.com.crescer.social.entity.entities.Reacao;
import br.com.crescer.social.entity.entities.Usuario;
import br.com.crescer.social.service.Exceptions.NoneexistentViewException;
import br.com.crescer.social.service.Service.AmigoService;
import br.com.crescer.social.service.Service.PostService;
import br.com.crescer.social.service.Service.UsuarioService;
import br.com.crescer.social.service.Utils.UsuarioUtils;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import org.springframework.security.core.userdetails.User;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
@RequestMapping(value = "postagem")
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    AmigoService amigoService;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    UsuarioUtils usuarioUtils;

    @RequestMapping(value = "carregarPosts", method = RequestMethod.GET)
    public String carregarPosts(Model model, Long id, String arquivo, Pageable pageable){
        Usuario usuario = usuarioService.findOne(id);

        Page<Post> posts = null;

        if (arquivo.equals("HOME")) {
            List<Perfil> perfisConsulta = usuario.getAmigos().stream()
                    .map(a -> a.getPerfil())
                    .collect(Collectors.toList());
            perfisConsulta.add(usuario.getPerfil());

            posts = postService.findByPerfisAutor(perfisConsulta, pageable);

        } else if (arquivo.equals("PERFIL")) {
            posts = new PageImpl(postService.findAllByPerfilAutor(usuario.getPerfil(), pageable).getContent()
                    .stream().sorted((p1, p2) -> p2.getId().compareTo(p1.getId()))
                    .collect(Collectors.toList()),
                    pageable, usuario.getPosts().size());
        } 
        else{
            throw new NoneexistentViewException("View inexistente!");
        }
        model.addAttribute("usuario", usuario);
        model.addAttribute("posts", posts);

        return "partialPostagem";
    }

    @RequestMapping(value = "carregarBtnUploadImagem", method = RequestMethod.GET)
    public String carregarBtnUploadImagem(Model model) {
        return "partialBtnUploadImagemPost";
    }

    @RequestMapping(value = "curtir", method = RequestMethod.GET)
    public String curtir(Model model, Long idUsuario, Long idPost, boolean curtir) {
        Post post = postService.findOne(idPost);
        Reacao reacao = post.getReacao();

        List<Perfil> perfis = reacao.getPerfisCurtidas();
        Usuario usuario = usuarioUtils.getUsuarioLogado();

        if (curtir) {
            perfis.add(usuario.getPerfil());
            reacao.setNumCutidas(reacao.getNumCutidas() + 1);
        } else {
            perfis.remove(usuario.getPerfil());
            reacao.setNumCutidas(reacao.getNumCutidas() - 1);
        }

        reacao.setPerfisCurtidas(perfis);

        post.setReacao(reacao);

        postService.save(post);

        model.addAttribute("post", post);
        model.addAttribute("usuario", usuario);

        return "partialReacoesPostagem";
    }
}
