/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.ChaveDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Chave;


@Stateless
public class ChaveControle{

    /**
     * Creates a new instance of ChaveControle
     */

    @EJB
    private ChaveDAO chaveDAO;

    public List<Chave> getChaves() { 
        try {
          return  chaveDAO.listarTodos();
        } catch (Exception e) {
            return null;
        }
        
        
    }


    public Chave getChave(Integer id) throws Exception {
        return chaveDAO.carregar(id);
    }

    public Boolean excluir(Chave chave) {
        try {
            chaveDAO.excluir(chave);
            return true;
        } catch (Exception e) {
            return false;
        }
        
        
        
    }

    public boolean salvar(Chave chave) {
        try {
            chaveDAO.salvar(chave);
            return true;
        } catch (Exception e) {
            return false;
        }
        
        
    }



    
}
