/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import modelo.TipoUsuario;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author dflenzi
 */
@Stateless
public class TipoUsuarioControle{
    @EJB
    private dao.JPADAO conexao;
    @EJB
    private dao.TipoUsuarioDAO tipoUsuarioDAO;
    /**
     * Creates a new instance of CategoriaBean
     */
    public List<TipoUsuario> getTipoUsuarios() throws Exception{
        return tipoUsuarioDAO.listarTodos();
    }


    public boolean excluir(TipoUsuario oTipoUsuario) throws Exception{
        try {
           tipoUsuarioDAO.excluir(oTipoUsuario);
           return true;
        } catch (Exception e) {
            return false;
        }
        
    }

    public TipoUsuario buscarTipoUsuario(int idTipoUsuario) throws Exception{
        return tipoUsuarioDAO.carregar(idTipoUsuario);
    }
    
    
}
