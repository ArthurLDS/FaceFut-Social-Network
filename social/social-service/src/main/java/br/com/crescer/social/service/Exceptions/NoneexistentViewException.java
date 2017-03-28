/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.service.Exceptions;

/**
 *
 * @author Arthur
 */
public class NoneexistentViewException extends RuntimeException{
    
    public NoneexistentViewException(String message){
        super(message);
    }
}
