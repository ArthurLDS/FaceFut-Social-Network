/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.web;

import br.com.crescer.social.entity.Amigo;
import br.com.crescer.social.service.Service.AmigoService;
import br.com.crescer.social.service.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Arthur
 */
@Controller
@RequestMapping(value="pesquisa")
public class PesquisaAmigoController {
    @Autowired
    UsuarioService usuarioService;
    
    @Autowired
    AmigoService amigoService;
    
    @RequestMapping(value="pesquisarAmigo", method = RequestMethod.GET)
    public String pesquisarAmigo(Model model, String filtro){
        Iterable<Amigo> amigos = amigoService.findByNome(filtro);
        model.addAttribute("amigos", amigos);
        return "partialPesquisarAmigos";
    }
}
