/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Arthur
 */
@Entity
@Table(name = "CONVITE")
public class Convite implements Serializable {

    public Convite(Date data, Perfil perfilRemetente, Perfil perfilDestinatario) {
        this.data = data;
        this.perfilRemetente = perfilRemetente;
        this.perfilDestinatario = perfilDestinatario;
    }

    public Convite() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONVITE")
    @SequenceGenerator(name = "SEQ_CONVITE", sequenceName = "SEQ_CONVITE", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_CONVITE")
    private Long id;

    @NotNull
    @Basic(optional = false)
    @Column(name = "DATA_CONVITE")
    private Date data;

    @JoinColumn(name = "ID_PERFIL_REMETENTE", nullable = false, foreignKey = @ForeignKey(name = "FK_CONVITE_PERFIL_REMETENTE"))
    @ManyToOne(targetEntity = Perfil.class)
    private Perfil perfilRemetente;

    @JoinColumn(name = "ID_PERFIL_DESTINATARIO", nullable = false, foreignKey = @ForeignKey(name = "FK_CONVITE_PERFIL_DESTINATARIO"))
    @ManyToOne(targetEntity = Perfil.class)
    private Perfil perfilDestinatario;

    public Long getId() {
        return id;
    }
    public Date getData() {
        return data;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setData(Date data) {
        this.data = data;
    }

    public Perfil getPerfilDestinatario() {
        return perfilDestinatario;
    }

    public void setPerfilDestinatario(Perfil perfilDestinatario) {
        this.perfilDestinatario = perfilDestinatario;
    }
    public Perfil getPerfilRemetente() {
        return perfilRemetente;
    }

    public void setPerfilRemetente(Perfil perfilRemetente) {
        this.perfilRemetente = perfilRemetente;
    }
    
    
}
