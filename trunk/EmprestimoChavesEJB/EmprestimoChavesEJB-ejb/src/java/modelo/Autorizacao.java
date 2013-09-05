/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;


import javax.persistence.Table;
import javax.persistence.Temporal;

@NamedQueries(
        {@NamedQuery(name = "autorizacao.listar",
                    query="select a from Autorizacao a")
        ,

        @NamedQuery(name = "autorizacao.usuario",
                    query="select a from Autorizacao a where a.usuario = (:usuario)"),
        
        @NamedQuery(name = "autorizacao.chavesusuario",
                    query="Select a.chave From Autorizacao a where a.usuario.codigo = :pcodigo " + 
                          " and (a.dataFim is null or a.dataFim > :pdata)  and " + 
                           " not exists (Select e From Emprestimo e Where e.chave = a.chave and e.dataDevolucao is null)")
        
    }        
  )



/**
 *
 * @author daniel
 */
@Entity
@Table(name="autorizacao")
public class Autorizacao implements Serializable{
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
    @Column(name="dataFim",nullable = true)
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Autorizacao other = (Autorizacao) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
        return hash;
    }

    
    
    
}
