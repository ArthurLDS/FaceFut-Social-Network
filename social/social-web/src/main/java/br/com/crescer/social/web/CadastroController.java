/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.web;

import br.com.crescer.social.entity.entities.Perfil;
import br.com.crescer.social.entity.entities.Time;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.crescer.social.entity.entities.Usuario;
import br.com.crescer.social.entity.enumeration.Sexo;
import br.com.crescer.social.service.Service.AmigoService;
import br.com.crescer.social.service.Service.PerfilService;
import br.com.crescer.social.service.Service.TimeService;
import br.com.crescer.social.service.Service.UsuarioService;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
        
        List<Sexo> generos = new ArrayList<>();
        generos.add(Sexo.FEMININO);
        generos.add(Sexo.MASCULINO);
        model.addAttribute("generos", generos);
        
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        Iterable<Time> times = timeService.findAll();
        model.addAttribute("times", times);

        return "cadastro";
    }

    @RequestMapping(value = "/cadastroForm", method = RequestMethod.POST)
    public String save(@ModelAttribute Usuario usuario, MultipartFile uploadfile, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors() && perfilService.salvarArquivo(uploadfile)) {
            
            Perfil perfil = perfilService.save(usuario, uploadfile);
            
            usuario.setPerfil(perfil);
            usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
            
            service.save(usuario);
            
            String nome = usuario.getPerfil().getNome().split(" ")[0];
            redirectAttributes.addFlashAttribute("msg", nome + " foi salvo(a) com sucesso!");
            return "redirect:cadastro";
        }
        return "redirect:cadastro";
    }
}
