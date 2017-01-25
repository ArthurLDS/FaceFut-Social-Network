/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.web;

import br.com.crescer.social.entity.entities.Amigo;
import br.com.crescer.social.entity.entities.Post;
import br.com.crescer.social.entity.entities.Time;
import br.com.crescer.social.entity.entities.Usuario;
import br.com.crescer.social.entity.entities.Convite;
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
    UsuarioService usuarioService;
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    String home(Model model) {
        User userAtual = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Usuario usuario = usuarioService.findByEmail(userAtual.getUsername());
        model.addAttribute("usuario", usuario);
        return "home";
    }
}
