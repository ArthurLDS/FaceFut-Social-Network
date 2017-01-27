/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service.Enumetarion;

/**
 *
 * @author Arthur
 */
public enum TipoArquivo {
    
    POST_IMG_FILE("Post_img_file"),
    PERFIL_IMG_FILE("Perfil_img_file");
    
    private final String nome;
    
    TipoArquivo(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    
}
