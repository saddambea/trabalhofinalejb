/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.JPADAO;
import javax.faces.bean.ManagedBean;
import modelo.TipoUsuario;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author dflenzi
 */
@ManagedBean
@SessionScoped
@Stateless
public class TipoUsuarioControle {
    @EJB
    private JPADAO conexao;
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
