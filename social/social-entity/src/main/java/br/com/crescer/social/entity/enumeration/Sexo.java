/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.entity.enumeration;

/**
 *
 * @author Arthur
 */
public enum Sexo {
    
    Feminino("Feminino"),
    Masculino("Masculino");
    
    private final String sexo;
    
    private Sexo(String s){
        sexo = s;
    }
}
