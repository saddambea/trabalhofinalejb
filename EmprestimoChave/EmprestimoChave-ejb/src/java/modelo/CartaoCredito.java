/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author dflenzi
 */
@Entity
@Table(name="cartaocredito")
public class CartaoCredito {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)     
    
    private int id;
    @Column(name="numero")
    private String numero;
    @Column(name="codigoSeguranca")
    private String codigoSeguranca;
    
    @Column(name="dataValidade")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataValidade;
    
    @OneToOne(mappedBy = "cartaoCredito", cascade = CascadeType.ALL)
    private Cliente cliente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public void setCodigoSeguranca(String codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
    
}
