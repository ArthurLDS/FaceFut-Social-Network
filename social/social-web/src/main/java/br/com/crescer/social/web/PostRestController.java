/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.web;

import br.com.crescer.social.entity.entities.Perfil;
import br.com.crescer.social.entity.entities.Post;
import br.com.crescer.social.entity.entities.Reacao;
import br.com.crescer.social.entity.entities.Usuario;
import br.com.crescer.social.service.Enumetarion.TipoArquivo;
import br.com.crescer.social.service.Service.PostService;
import br.com.crescer.social.service.Service.ReacaoService;
import br.com.crescer.social.service.Utils.FileUtils;
import br.com.crescer.social.service.Service.UsuarioService;
import br.com.crescer.social.service.Utils.UsuarioUtils;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Arthur
 */
@RestController
@RequestMapping(value="postagemRest")
public class PostRestController {
    
    @Autowired
    PostService postService;
    @Autowired
    ReacaoService reacaoService;
    @Autowired
    UsuarioService usuarioService;
    
    @RequestMapping(value="postar", method = RequestMethod.POST)
    public void postar(String caminhoImagem, String texto){
        
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuarioLogado = usuarioService.findByEmail(user.getUsername());
        
        //Refatorar Contronstrutor...
        Post post = postService.salvar(caminhoImagem, texto, usuarioLogado);
        post.setReacao(new Reacao());
        postService.save(post);
        
        List<Post> postsUsuario = usuarioLogado.getPosts();
        postsUsuario.add(post);
        usuarioLogado.setPosts(postsUsuario);
        
        usuarioService.save(usuarioLogado);
        
        
    }
    
    @RequestMapping(value="uploadImagem", method = RequestMethod.POST)
    public String uploadImagem(MultipartFile uploadfile){
        FileUtils fileUtils = new FileUtils();
        
        return fileUtils.salvarArquivo(uploadfile, TipoArquivo.POST_IMG_FILE) 
               ? uploadfile.getOriginalFilename() : null;
    }
    
    /*@RequestMapping(value="curtir", method = RequestMethod.POST)
    public Integer curtirPost(Long id){
        Post post = postService.findOne(id);
        Reacao reacao = post.getReacao();
        
        List<Perfil> perfis =  reacao.getPerfisCurtidas();
        Usuario usuario = usuarioUtils.getUsuarioLogado(); 
        perfis.add(usuario.getPerfil());
        
        reacao.setNumCutidas(reacao.getNumCutidas()+1);
        reacao.setPerfisCurtidas(perfis);
        
        post.setReacao(reacao);
        postService.save(post);
        
        return post.getReacao().getNumCutidas();
    }*/
}
