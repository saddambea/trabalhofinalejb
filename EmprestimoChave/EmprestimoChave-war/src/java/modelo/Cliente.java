/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author dflenzi
 */
@Entity
@Table(name="cliente")
public class Cliente {
    @Id
    @Column(name="cd_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name="nm_cliente" , nullable=false, length=100, unique=true)
    private String nome;
    
    @Column(name="dt_nascimento")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataNascimento;
    
    @Column(name="vl_disponivel" ,columnDefinition="decimal(5,2) not null"  )
    private double valorDisponivel;
    
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
    
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "cartaocredito_id")    
    private CartaoCredito cartaoCredito;

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public double getValorDisponivel() {
        return valorDisponivel;
    }

    public void setValorDisponivel(double valorDisponivel) {
        this.valorDisponivel = valorDisponivel;
    }
    
    
    public Cliente() {
        endereco = new Endereco();
        cartaoCredito = new CartaoCredito();
    }

    public CartaoCredito getCartaoCredito() {
        return cartaoCredito;
    }

    public void setCartaoCredito(CartaoCredito cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }

    
    @Override
    public String toString() {
        return "Cliente: Nome:" + nome + ". \n Nascimento:" + dataNascimento + ". \n Valor: " + valorDisponivel + ".  \n"  + endereco;
    }
    
    

    
}
