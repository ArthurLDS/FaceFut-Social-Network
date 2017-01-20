/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.entity;

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
@Table(name = "PERFIL")
public class Perfil {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PERFIL")
    @SequenceGenerator(name = "SEQ_PERFIL", sequenceName = "SEQ_PERFIL", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_PERFIL")
    private Long id;
    
    /*@NotNull
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
    private List<Convite> convitesEnviados;*/
    
    @Basic(optional = false)
    @Column(name="IMAGEM_PERFIL")
    private String imagemPerfil;

    public void setId(Long id) {
        this.id = id;
    }

    public void setImagemPerfil(String imagemPerfil) {
        this.imagemPerfil = imagemPerfil;
    }
    
    public Long getId() {
        return id;
    }

    public String getImagemPerfil() {
        return imagemPerfil;
    }
}
