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
import javax.validation.constraints.NotNull;

/**
 *
 * @author Arthur
 */
@Entity
public class Convite implements Serializable{
    
    public Convite(String remetente, String destinatario, Date data, Perfil perfil){
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.data = data;
        this.perfilRemetente = perfil;
    }
    
    public Convite(){
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONVITE")
    @SequenceGenerator(name = "SEQ_CONVITE", sequenceName = "SEQ_CONVITE", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_CONVITE")
    private Long id;
    
    @NotNull
    @Basic(optional = false)
    @Column(name = "REMETENTE")
    private String remetente;
    
    @NotNull
    @Basic(optional = false)
    @Column(name = "DESTINATARIO")
    private String destinatario;
    
    @NotNull
    @Basic(optional = false)
    @Column(name = "DATA_CONVITE")
    private Date data;
    
    @JoinColumn(name = "ID_PERFIL", nullable = false, foreignKey = @ForeignKey(name="FK_CONVITE_PERFIL_REMETENTE"))
    @ManyToOne(targetEntity = Perfil.class)
    private Perfil perfilRemetente;

    public Perfil getPerfilRemetente() {
        return perfilRemetente;
    }

    public void setPerfilRemetente(Perfil perfilRemetente) {
        this.perfilRemetente = perfilRemetente;
    }
    
    public Long getId() {
        return id;
    }

    public String getRemetente() {
        return remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public Date getData() {
        return data;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
