/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.JPADAO;
import java.util.ArrayList;
import modelo.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import modelo.Autorizacao;

/**
 *
 * @author dflenzi
 */
@Stateful
public class UsuarioControle{

    /**
     * Creates a new instance of UsuarioControle
     */
    @EJB
    private dao.JPADAO conexao;
    

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

    public List<Autorizacao> getAutorizacoes(Usuario usuario) {
       List<Autorizacao> lista = new ArrayList<Autorizacao>();
       for(Autorizacao aut : conexao.listarTodos(Autorizacao.class)){
           if(aut.getUsuario().getId() == usuario.getId()){
             lista.add(aut);
           }
       }
       return lista;
    }

    
    
    
}
