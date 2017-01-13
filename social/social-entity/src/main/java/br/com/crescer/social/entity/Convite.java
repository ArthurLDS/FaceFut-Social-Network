/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Arthur
 */
@Entity
public class Convite implements Serializable{
    
    public Convite(String remetente, String destinatario, Date data){
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.data = data;
    }
    
    public Convite(){
        
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONVITE")
    @SequenceGenerator(name = "SEQ_CONVITE", sequenceName = "SEQ_CONVITE", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_CONVITE")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "REMETENTE")
    private String remetente;
    
    @Basic(optional = false)
    @Column(name = "DESTINATARIO")
    private String destinatario;
    
    @Basic(optional = false)
    @Column(name = "DATA_CONVITE")
    private Date data;

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
