/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author daniel
 */
@Entity
@Table(name="autorizacao")
public class Autorizacao {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private int id;
    
    @OneToOne
    @JoinColumn(name = "usuario_id")    
    private Usuario usuario;
    
    @OneToOne
    @JoinColumn(name = "chave_id")
    private Chave chave;
    
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="dataInicio")
    private Date dataInicio;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="dataFim")
    private Date dataFim;  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Chave getChave() {
        return chave;
    }

    public void setChave(Chave chave) {
        this.chave = chave;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
    
    
    
}
