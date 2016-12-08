/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.crescer.social.entity.Usuario;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 *
 * @author Arthur
 */
@Controller
public class CadastroController {
    
    @RequestMapping(value="/cadastro")
    String cadastro(Model model){
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        
        return "cadastro";
    }
    
     @RequestMapping(value="/cadastro", method= RequestMethod.POST)
     public String save(@ModelAttribute Usuario usuario){
         return "cadastro";
     }
}
