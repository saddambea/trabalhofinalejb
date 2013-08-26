/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.JPADAO;
import javax.faces.bean.ManagedBean;
import modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import modelo.Autorizacao;
import modelo.TipoUsuario;

/**
 *
 * @author dflenzi
 */
@Stateful
public class UsuarioControle {

    /**
     * Creates a new instance of UsuarioControle
     */
    @EJB
    private JPADAO conexao;
    

    public List<Usuario> getUsuarios() {
        return conexao.listarTodos(Usuario.class);
    }



    public Boolean excluir(Usuario usuario) {
        try {
            conexao.excluir(usuario);
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }

    public boolean salvar(Usuario usuario) {
        try {
            conexao.salvar(usuario);
            return true;
        } catch (Exception e) {
            return false;
        }
  
        
    }

    public Usuario buscarUsuario(int idUsuario) {
        return conexao.procurar(Usuario.class, idUsuario);
    }

   
    
    public Usuario getUsuarioByCodigo(int codigo){        
        return conexao.buscarSimples(Usuario.class, "codigo", codigo);
    }
    
    
    
}
