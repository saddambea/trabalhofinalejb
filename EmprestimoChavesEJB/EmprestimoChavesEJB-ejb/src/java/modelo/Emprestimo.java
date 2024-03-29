/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
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
      {@NamedQuery(name = "emprestimos.listarativos",
                   query="select e from Emprestimo e where e.dataDevolucao is null"),

       @NamedQuery(name = "emprestimos.listarchave",
                   query="select e.chave from Emprestimo e where e.dataDevolucao is null"),

        

        @NamedQuery(name = "emprestimos.usuario",
                   query="select e from Emprestimo e where e.usuario = :usuario")

        }

 
)
/**
 *
 * @author daniel
 */
@Entity

@Table(name="emprestimo")
public class Emprestimo implements Serializable{
   @Id
   @Column(name="id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)    
   private int id;
   
   @OneToOne(cascade = CascadeType.ALL, optional = false)
   @JoinColumn(name = "chave_id")    
   private Chave chave;
   
   @OneToOne(cascade = CascadeType.ALL, optional = false)
   @JoinColumn(name = "balconista_id")       
   private Usuario balconista;
   
   @OneToOne(cascade = CascadeType.ALL, optional = false)
   @JoinColumn(name = "usuario_id")       
   private Usuario usuario;
   
   @Temporal(javax.persistence.TemporalType.TIMESTAMP)           
   @Column(name="dataEmprestimo")
   private Date dataEmprestimo;
   
   @Temporal(javax.persistence.TemporalType.TIMESTAMP)
   @Column(name="dataDevolucao")
   private Date dataDevolucao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
   public Chave getChave() {
        return chave;
    }

    public void setChave(Chave chave) {
        this.chave = chave;
    }

    public Usuario getBalconista() {
        return balconista;
    }

    public void setBalconista(Usuario balconista) {
        this.balconista = balconista;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.id;
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
        final Emprestimo other = (Emprestimo) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

   
    
    
}
