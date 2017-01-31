/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.entity.entities;

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

/**
 *
 * @author Arthur
 */
@Entity
@Table(name="REACAO")
public class Reacao implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_REACAO")
    @SequenceGenerator(name = "SEQ_REACAO", sequenceName = "SEQ_REACAO", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_REACAO")
    private Long id;
    
    @Basic(optional = true)
    @Column(name = "NUM_CURTIDAS")
    private int numCutidas;
    
    @ManyToMany
    private List<Perfil> perfisCurtidas;

    public Long getId() {
        return id;
    }

    public int getNumCutidas() {
        return numCutidas;
    }

    public List<Perfil> getPerfisCurtidas() {
        return perfisCurtidas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumCutidas(int numCutidas) {
        this.numCutidas = numCutidas;
    }

    public void setPerfisCurtidas(List<Perfil> perfisCurtidas) {
        this.perfisCurtidas = perfisCurtidas;
    }
    
    
    
}
