/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
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
import javax.persistence.Transient;

@NamedQueries(
                    @NamedQuery(name = "usuario.listarSimples",
                    query="select u from Usuario u where u.codigo = (:codigo)")
             )


@Entity
@Table(name="Usuario")
public class Usuario implements Serializable{
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private int id;
    
    @Column(name="codigo", unique = true, nullable = true)
    private Integer codigo;
    
    @Column(name="nome")
    private String nome;
    @Column(name="email")
    private byte[] email;
    
    @Transient
    //@Column(name="emailstr")
    private String emailstr;
    
    @Column(name="senha")
    private int senha;
    
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "tipo_id")    
    private TipoUsuario tipo;

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

    public byte[] getEmail() {
        return email;
    }

    public void setEmail(byte [] email) {
        this.email = email;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Usuario(Integer codigo, String nome, byte[] email, int senha, TipoUsuario tipo) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    
    }

    public String getEmailstr(){ 
      return emailstr;   
    }
    

    public void setEmailstr(String emailstr) {
        this.emailstr = emailstr;
    }
    
    
    
    public Usuario() {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        return hash;
    }
    
       
    
    
    
}
