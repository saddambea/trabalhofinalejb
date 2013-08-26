/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.JPADAO;
import javax.faces.bean.ManagedBean;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import modelo.Autorizacao;

/**
 *
 * @author dflenzi
 */
@ManagedBean
@SessionScoped
@Stateless
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
