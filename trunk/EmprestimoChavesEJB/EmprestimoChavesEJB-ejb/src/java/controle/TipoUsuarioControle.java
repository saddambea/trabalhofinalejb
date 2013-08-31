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
    private dao.JPADAOXX conexao;
    /**
     * Creates a new instance of CategoriaBean
     */
    public List<TipoUsuario> getTipoUsuarios() {
        return conexao.listarTodos(TipoUsuario.class);
    }


    public boolean excluir(TipoUsuario oTipoUsuario) {
        try {
           conexao.excluir(oTipoUsuario);
           return true;
        } catch (Exception e) {
            return false;
        }
        
    }

    public TipoUsuario buscarTipoUsuario(int idTipoUsuario) {
        return conexao.procurar(TipoUsuario.class, idTipoUsuario);
    }
    
    
}
