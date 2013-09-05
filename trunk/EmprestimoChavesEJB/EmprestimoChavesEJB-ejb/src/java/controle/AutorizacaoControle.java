/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.AutorizacaoDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Autorizacao;

/**
 *
 * @author dflenzi
 */

@Stateless
public class AutorizacaoControle{

    /**
     * Creates a new instance of UsuarioBean
     */
    @EJB
    AutorizacaoDAO autDAO;
    

 
    public AutorizacaoControle() {
    }

    public List<Autorizacao> getAutorizacoes() {
        try {
            return autDAO.listarTodos();
        } catch (Exception e) {
            return null;
        }
        
    }


    public Autorizacao getAutorizacao(Integer id) {        
        try {
            return autDAO.carregar(id);
        } catch (Exception e) {
            return null;
        }
        
    }

    
    public boolean salvar(Autorizacao autorizacao){
        try {
         autDAO.salvar(autorizacao);
         return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean excluir(Autorizacao oAutorizacao) {
        try {
            autDAO.excluir(oAutorizacao);
        } catch (Exception e) {
        }
        return true;      
        
    }

    
}
