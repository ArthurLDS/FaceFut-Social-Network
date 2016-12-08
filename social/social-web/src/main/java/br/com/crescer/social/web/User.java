package br.com.crescer.social.web;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Carlos H. Nonnemacher
 */
public class User {
    
    @NotEmpty()
    private String username;
    
    @NotEmpty()
    private String senha;

    public String getUsername() {
        return username;
    }

    public String getSenha() {
        return senha;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    

    
}
