/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Arthur
 */
@Entity
public class Amigo implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AMIGO")
    @SequenceGenerator(name = "SEQ_AMIGO", sequenceName = "SEQ_AMIGO", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_AMIGO")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "NM_AMIGO")
    private String nome;
    
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;
    
    @NotNull
    @JoinColumn(name = "ID_TIME", nullable = false, foreignKey = @ForeignKey(name="FK_USUARIO_TIME"))
    @ManyToOne(targetEntity = Time.class)
    private Time time;
    
    @OneToMany(cascade = ALL)
    private List<Post> posts;
    
    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
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

    public Time getTime() {
        return time;
    }

    public List<Post> getPosts() {
        return posts;
    }
    
}
