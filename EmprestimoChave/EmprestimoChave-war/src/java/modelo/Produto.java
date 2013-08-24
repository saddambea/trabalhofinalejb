/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.persistence.*;

/**
 *
 * @author dflenzi
 */
@Entity
@Table(name="produto")

public class Produto {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private int id;
    @Column(name="descricao" ,unique=true)
    private String descricao;
    @Column(name="qtdEstoque")
    private int qtdEstoque;
    @Column(name="valorUnitario")
    private double valorUnitario;

    public Produto() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    
    
    
    }
    
   public Produto(String descricao, int qtdEstoque, double valorUnitario) {        
        this.descricao = descricao;
        this.qtdEstoque = qtdEstoque;
        this.valorUnitario = valorUnitario;
    }


           
    
}
