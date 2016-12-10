/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.web;

import br.com.crescer.social.entity.Time;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.crescer.social.entity.Usuario;
import br.com.crescer.social.service.Service.TimeService;
import br.com.crescer.social.service.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/**
 *
 * @author Arthur
 */
@Controller
public class CadastroController {
    
    @Autowired
    UsuarioService service;
    
    @Autowired
    TimeService timeService =  new TimeService();
    
    @RequestMapping(value="/cadastro")
    String cadastro(Model model){
        
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        Iterable<Time> times = timeService.findAll();
        model.addAttribute("times", times);
        
        return "cadastro";
    }
    
    
     @RequestMapping(value="/cadastro", method= RequestMethod.POST)
     public String save(@ModelAttribute Usuario usuario, BindingResult bindingResult, RedirectAttributes redirectAttributes){
         
         if (!bindingResult.hasErrors()){
             
            usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
            
            service.save(usuario);
            String nome = usuario.getNome().split(" ")[0];
            redirectAttributes.addFlashAttribute("msg",  nome + " foi salvo(a) com sucesso!");
            return "redirect:cadastro";
        }
        return "cadastro";
     }
}
