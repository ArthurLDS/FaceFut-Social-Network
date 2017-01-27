/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service.Service;

import br.com.crescer.social.entity.entities.Perfil;
import br.com.crescer.social.entity.entities.Usuario;
import br.com.crescer.social.service.Repository.PerfilRepository;
import java.io.BufferedOutputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
/**
 *
 * @author Arthur
 */
@Service
public class PerfilService {

    @Autowired
    PerfilRepository perfilRepository;
    
    public Perfil findById(Long id){
        return perfilRepository.findOne(id);
    }
    
    public Iterable<Perfil> findByNomeIgnoreCaseContaining(String nome){
        return perfilRepository.findByNomeIgnoreCaseContaining(nome);
    }
        
    public void save(Perfil perfil) {
        perfilRepository.save(perfil);
    }

    public Perfil save(Usuario usuario, MultipartFile multipartFile) {
        Perfil perfil = usuario.getPerfil();
        perfil.setEmail(usuario.getEmail());
        perfil.setImagemPerfil("/imgs/" + multipartFile.getOriginalFilename());
        
        return perfilRepository.save(perfil);
    }
}
