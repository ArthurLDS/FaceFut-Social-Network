/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.entity.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Arthur
 */
@Entity
@Table(name = "TIME")
public class Time implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TIME")
    @SequenceGenerator(name = "SEQ_TIME", sequenceName = "SEQ_TIME", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_TIME")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "NM_TIME")
    private String nome;
    
    @Basic(optional = false)
    @Column(name = "ESCUDO_TIME")
    private String escudo;
    
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEscudo() {
        return escudo;
    }

    public void setEscudo(String escudo) {
        this.escudo = escudo;
    }
    
    
}
