/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service.Utils;

import br.com.crescer.social.entity.entities.Usuario;
import br.com.crescer.social.service.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
/**
 *
 * @author Arthur
 */
@Service
public class UsuarioUtils {
    
    @Autowired
    UsuarioService usuarioService;
    
    public Usuario getUsuarioLogado(){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return usuarioService.findByEmail(user.getUsername());
    
    }
}
