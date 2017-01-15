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
import br.com.crescer.social.service.Service.ConviteService;
import br.com.crescer.social.service.Service.UsuarioService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Arthur
 */
@RestController
@RequestMapping(value = "/amigoRest")
public class AmigoRestController {

    @Autowired
    AmigoService amigoService;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    ConviteService conviteService;

    @RequestMapping(value = "/enviarConvite", method = RequestMethod.PUT)
    public void enviarConvite(Long id) {

        Amigo amigoDestinatario = amigoService.findById(id);

        Convite convite = new Convite(getUserSessao().getUsername(), amigoDestinatario.getEmail(), new Date());
        conviteService.save(convite);

        Usuario usuarioAtual = usuarioService.findByEmail(getUserSessao().getUsername());

        List<Convite> convitesUsuario = usuarioAtual.getConvites();
        convitesUsuario.add(convite);
        usuarioAtual.setConvites(convitesUsuario);
        usuarioService.save(usuarioAtual);
    }

    @RequestMapping(value = "/aceitarConvite", method = RequestMethod.POST)
    public void aceitarConvite(Long id) {
        
        //Aceitando e adicionando amigo no Usuario atual
        Convite conviteAprovado = conviteService.findById(id);

        Amigo amigoRemetente = amigoService.findFirstByEmail(conviteAprovado.getRemetente());
        Usuario usuarioEntity = usuarioService.findByEmail(getUserSessao().getUsername());
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
        
        List<Convite> convites = usuarioRemetente.getConvites();
        convites.remove(conviteAprovado);        
        usuarioRemetente.setConvites(convites);
        
        usuarioService.save(usuarioRemetente);

        //Agora excluimos o convite
        conviteService.deleteConvite(conviteAprovado);
    }
    
    @RequestMapping(value="/recusarConvite", method = RequestMethod.POST)
    public void recusarConvite(Long id){
        
        Convite conviteReprovado = conviteService.findById(id);
        Usuario usuario = usuarioService.findByEmail(conviteReprovado.getRemetente());
        
        List<Convite> convites = usuario.getConvites();
        convites.remove(conviteReprovado);        
        usuario.setConvites(convites);
        
        usuarioService.save(usuario);
        conviteService.deleteConvite(conviteReprovado);
    }

    @RequestMapping(value = "/atualizarNumAmigos", method = RequestMethod.GET)
    public Integer atualizarNumAmigos() {
        Usuario usuario = usuarioService.findByEmail(getUserSessao().getUsername());
        return usuario.getAmigos().size();
    }

    private User getUserSessao() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    
}
