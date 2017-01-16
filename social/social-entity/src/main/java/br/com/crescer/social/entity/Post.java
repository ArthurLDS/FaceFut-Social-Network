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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author arthur.souza
 */
@Entity
@Table(name = "POST")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_POST")
    @SequenceGenerator(name = "SEQ_POST", sequenceName = "SEQ_POST", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_POST")
    private Long id;

    @NotNull
    @Basic(optional = false)
    @Column(name = "AUTOR")
    private String autor;

    @NotNull
    @Size(min = 1, max = 255, message = "O mínimo de caracteres é 1 e o máximo é 255.")
    @Basic(optional = false)
    @Column(name = "TEXTO")
    private String texto;

    @NotNull
    @Basic(optional = false)
    @Column(name = "DATA_POST")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data;

    @NotNull
    @Basic(optional = false)
    @Column(name = "TIME")
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public String getAutor() {
        return autor;
    }

    public String getTexto() {
        return texto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

}
