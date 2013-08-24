/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author dflenzi
 */
@Entity
@Table(name="endereco")

public class Endereco {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)     
    private int id;
    @Column(name="rua")
    private String rua;
    @Column(name="cidade")
    private String cidade;
    @Column(name="estado")
    private String estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Endereco: " + "Rua: " + rua + ". \n Cidade:" + cidade + ". \n Estado=" + estado;
    }
            
    
    
}
