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

        Usuario usuarioRemetente = usuarioService.findByEmail(getUserSessao().getUsername());
        Usuario usuarioDestinatario = usuarioService.findByEmail(amigoDestinatario.getEmail());

        Convite convite = new Convite(new Date(), usuarioRemetente.getPerfil(), usuarioDestinatario.getPerfil());
        conviteService.save(convite);

        //Adicionando convite no usuario Destinatario
        conviteService.adicionarConviteUsuario(usuarioDestinatario, convite, "DESTINATARIO");
        //Adicionando convite no usuario rementente
        conviteService.adicionarConviteUsuario(usuarioRemetente, convite, "REMETENTE");
    }

    @RequestMapping(value = "/aceitarConvite", method = RequestMethod.POST)
    public void aceitarConvite(Long id) {

        Convite conviteAprovado = conviteService.findById(id);
        
        //Aceitando e adicionando amigo no Usuario atual
        Amigo amigoRemetente = amigoService.findFirstByEmail(conviteAprovado.getPerfilRemetente().getEmail());
        Usuario usuarioDestinatario = usuarioService.findByEmail(getUserSessao().getUsername());
        
        amigoService.adicionarAmigo(usuarioDestinatario, amigoRemetente, conviteAprovado, "DESTINATARIO");

        //Adicionando amigo no Usuario que enviou o convite..
        Amigo amigoDestinatario = amigoService.findFirstByEmail(conviteAprovado.getPerfilDestinatario().getEmail());
        Usuario usuarioRemetente = usuarioService.findByEmail(conviteAprovado.getPerfilRemetente().getEmail());

        amigoService.adicionarAmigo(usuarioRemetente, amigoDestinatario, conviteAprovado, "REMETENTE");
        
        conviteService.deleteConvite(conviteAprovado);
    }

    @RequestMapping(value = "/recusarConvite", method = RequestMethod.POST)
    public void recusarConvite(Long id) {

        Convite conviteReprovado = conviteService.findById(id);
        
        Usuario usuarioDestinatario = usuarioService.findByEmail(getUserSessao().getUsername());
        Usuario usuarioRemetente = usuarioService.findByEmail(conviteReprovado.getPerfilRemetente().getEmail());

        List<Convite> convitesDestinatario = usuarioDestinatario.getConvitesRecebidos();
        convitesDestinatario.remove(conviteReprovado);
        usuarioDestinatario.setConvitesRecebidos(convitesDestinatario);
        
        List<Convite> convitesRemetente = usuarioRemetente.getConvitesEnviados();
        convitesRemetente.remove(conviteReprovado);
        usuarioRemetente.setConvitesEnviados(convitesRemetente);

        conviteService.deleteConvite(conviteReprovado);
        usuarioService.save(usuarioDestinatario);
        usuarioService.save(usuarioRemetente);
    }

    @RequestMapping(value = "/desfazerAmizade", method = RequestMethod.POST)
    public void desfazerAmizade(Long id) {
        
        //Deletando relação de amizade na primeira extremidade.
        Usuario usuarioSolicitante = usuarioService.findByEmail(getUserSessao().getUsername());
        Amigo amigoSolicitado = amigoService.findById(id);

        amigoService.removerAmigo(usuarioSolicitante, amigoSolicitado);
        
        //Deletando relação de amizade na outra extremidade.
        Usuario usuarioSolicitado = usuarioService.findByEmail(amigoSolicitado.getEmail());
        Amigo amigoSolicitante = amigoService.findFirstByEmail(usuarioSolicitante.getEmail());
        
        amigoService.removerAmigo(usuarioSolicitado, amigoSolicitante);
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
