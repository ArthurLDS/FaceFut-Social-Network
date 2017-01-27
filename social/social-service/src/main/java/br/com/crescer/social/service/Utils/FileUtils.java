/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service.Utils;

import br.com.crescer.social.service.Enumetarion.TipoArquivo;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Arthur
 */
public class FileUtils {
   
    public boolean salvarArquivo(MultipartFile arquivo, TipoArquivo tipoArquivo) {
        try {
            String nomeArquivo = arquivo.getOriginalFilename();
            String diretorioArquivo = "";
            if(tipoArquivo == TipoArquivo.PERFIL_IMG_FILE){
                diretorioArquivo = "C:\\Users\\Arthur\\Documents\\GitHub\\FaceFut\\FaceFut\\social\\social-web\\src\\main\\resources\\static\\imgs\\perfil";
            }
            else if(tipoArquivo == TipoArquivo.POST_IMG_FILE){
                diretorioArquivo = "C:\\Users\\Arthur\\Documents\\GitHub\\FaceFut\\FaceFut\\social\\social-web\\src\\main\\resources\\static\\imgs\\post";
            }
            String caminhoArquivo = Paths.get(diretorioArquivo, nomeArquivo).toString();

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(caminhoArquivo)));
            stream.write(arquivo.getBytes());
            stream.close();

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    
}
