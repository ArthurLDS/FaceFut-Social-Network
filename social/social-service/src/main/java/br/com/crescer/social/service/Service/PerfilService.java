/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service.Service;

import br.com.crescer.social.entity.Perfil;
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

    public void save(Perfil perfil) {
        perfilRepository.save(perfil);
    }

    public Perfil save(MultipartFile multipartFile) {
        Perfil perfil = new Perfil();
        perfil.setImagemPerfil("/imgs/" + multipartFile.getOriginalFilename());
        
        return perfilRepository.save(perfil);
    }

    public boolean salvarArquivo(MultipartFile arquivo) {
        try {
            String nomeArquivo = arquivo.getOriginalFilename();
            String diretorioArquivo = "C:\\Users\\Arthur\\Documents\\GitHub\\FaceFut\\FaceFut\\social\\social-web\\src\\main\\resources\\static\\imgs";
            String caminhoArquivo = Paths.get(diretorioArquivo, nomeArquivo).toString();

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(caminhoArquivo)));
            stream.write(caminhoArquivo.getBytes());
            stream.close();

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
