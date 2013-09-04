/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author daniel
 */
@Entity
@Table(name="tipoUsuario")
public class TipoUsuario implements Serializable{
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private int id;
    @Column(name="descricao")
    private String descricao;
    @Column(name="permUsuario")
    private boolean permUsuario;
    @Column(name="permChave")
    private boolean permChave;
    @Column(name="permAutorizacao")
    private boolean permAutorizacao;
    @Column(name="permEmprestimo")
    private boolean permEmprestimo;
    @Column(name="permDevolucao")
    private boolean permDevolucao;
    @Column(name="permConsEmpr")
    private boolean permConsEmpr;

    public TipoUsuario() {
        
    }

    public TipoUsuario(String descricao, boolean permUsuario, boolean permChave, boolean permAutorizacao, boolean permEmprestimo, boolean permDevolucao, boolean permConsEmpr) {
        this.descricao = descricao;
        this.permUsuario = permUsuario;
        this.permChave = permChave;
        this.permAutorizacao = permAutorizacao;
        this.permEmprestimo = permEmprestimo;
        this.permDevolucao = permDevolucao;
        this.permConsEmpr = permConsEmpr;
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

    public boolean getPermUsuario() {
        return permUsuario;
    }

    public void setPermUsuario(boolean permUsuario) {
        this.permUsuario = permUsuario;
    }

    public boolean getPermChave() {
        return permChave;
    }

    public void setPermChave(boolean permChave) {
        this.permChave = permChave;
    }

    public boolean getPermAutorizacao() {
        return permAutorizacao;
    }

    public void setPermAutorizacao(boolean permAutorizacao) {
        this.permAutorizacao = permAutorizacao;
    }

    public boolean getPermEmprestimo() {
        return permEmprestimo;
    }

    public void setPermEmprestimo(boolean permEmprestimo) {
        this.permEmprestimo = permEmprestimo;
    }

    public boolean getPermDevolucao() {
        return permDevolucao;
    }

    public void setPermDevolucao(boolean permDevolucao) {
        this.permDevolucao = permDevolucao;
    }

    public boolean getPermConsEmpr() {
        return permConsEmpr;
    }

    public void setPermConsEmpr(boolean permConsEmpr) {
        this.permConsEmpr = permConsEmpr;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoUsuario other = (TipoUsuario) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
}
