/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.web;

import br.com.crescer.social.entity.Perfil;
import br.com.crescer.social.entity.Time;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.crescer.social.entity.Usuario;
import br.com.crescer.social.service.Service.AmigoService;
import br.com.crescer.social.service.Service.PerfilService;
import br.com.crescer.social.service.Service.TimeService;
import br.com.crescer.social.service.Service.UsuarioService;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
    AmigoService amigoService;

    @Autowired
    TimeService timeService;

    @Autowired
    PerfilService perfilService;

    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    String cadastro(Model model) {

        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        Iterable<Time> times = timeService.findAll();
        model.addAttribute("times", times);

        return "cadastro";
    }

    @RequestMapping(value = "/cadastroForm", method = RequestMethod.POST)
    public String save(@ModelAttribute Usuario usuario, MultipartFile uploadfile, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors() && perfilService.salvarArquivo(uploadfile)) {
            
            Perfil perfil = perfilService.save(uploadfile);
            
            usuario.setPerfil(perfil);
            usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
            
            service.save(usuario);
            //Este método save fará um Parse de Usuario para Amigo.
            amigoService.save(usuario);

            String nome = usuario.getNome().split(" ")[0];
            redirectAttributes.addFlashAttribute("msg", nome + " foi salvo(a) com sucesso!");
            return "redirect:cadastro";
        }
        return "redirect:cadastro";
    }
}
