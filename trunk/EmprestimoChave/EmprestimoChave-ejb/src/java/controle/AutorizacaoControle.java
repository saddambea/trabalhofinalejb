/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.JPADAO;
import java.text.SimpleDateFormat;
import javax.faces.bean.ManagedBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import modelo.Usuario;
import modelo.Chave;
import modelo.Autorizacao;

/**
 *
 * @author dflenzi
 */
@ManagedBean
@SessionScoped
public class AutorizacaoControle {

    /**
     * Creates a new instance of UsuarioBean
     */
    @EJB
    JPADAO conexao;
    

 
    public AutorizacaoControle() {
    }

    public List<Autorizacao> getAutorizacoes() {
        return conexao.listarTodos(Autorizacao.class);
    }


    public Autorizacao getAutorizacao(Integer id) {        
        return conexao.procurar(Autorizacao.class, id);
    }

    
    public boolean inserir(Autorizacao autorizacao){
        conexao.salvar(autorizacao);
        return true;
    }
    
    public boolean excluir(Autorizacao oAutorizacao) {
        conexao.excluir(oAutorizacao);
        return true;      
        
    }

    
}
