/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Arthur
 */
@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIO")
    @SequenceGenerator(name = "SEQ_USUARIO", sequenceName = "SEQ_USUARIO", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_USUARIO")
    private Long id;

    @NotNull
    @Size(min = 1, max = 255, message = "O mínimo de caracteres é 1 e o máximo é 255.")
    @Basic(optional = false)
    @Column(name = "NM_USUARIO")
    private String nome;
    
    @NotNull
    @Size(min = 1, max = 255, message = "O mínimo de caracteres é 1 e o máximo é 255.")
    @Basic(optional = false)
    @Column(name = "EMAIL", unique = true)
    private String email;
    
    @NotNull
    @Size(min = 1, max = 255, message = "O mínimo de caracteres é 1 e o máximo é 255.")
    @Basic(optional = false)
    @Column(name = "SENHA")
    private String senha;
    
    @NotNull
    @JoinColumn(name = "ID_TIME", nullable = false, foreignKey = @ForeignKey(name="FK_USUARIO_TIME"))
    @ManyToOne(targetEntity = Time.class)
    private Time time;
    
    @ManyToMany
    private List<Amigo> amigos;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Post> posts;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Convite> convitesRecebidos;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Convite> convitesEnviados;

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setConvitesRecebidos(List<Convite> convitesRecebidos) {
        this.convitesRecebidos = convitesRecebidos;
    }

    public void setConvitesEnviados(List<Convite> convitesEnviados) {
        this.convitesEnviados = convitesEnviados;
    }
    
    public List<Post> getPosts() {
        return posts;
    }

    public List<Convite> getConvitesRecebidos() {
        return convitesRecebidos;
    }

    public List<Convite> getConvitesEnviados() {
        return convitesEnviados;
    }
    
    public List<Amigo> getAmigos() {
        return amigos;
    }

    public void setAmigos(List<Amigo> amigos) {
        this.amigos = amigos;
    }
    
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}