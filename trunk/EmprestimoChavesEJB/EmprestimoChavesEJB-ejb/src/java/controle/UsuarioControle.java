/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.JPADAOXX;
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
    private dao.JPADAOXX conexao;
    

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
       List<Autorizacao> todas =  new ArrayList<Autorizacao>();
       todas = conexao.listarTodos(Autorizacao.class);
       for(Autorizacao aut : todas){
           if (aut.getUsuario() != null){
            if(aut.getUsuario().getId() == usuario.getId()){
                 lista.add(aut);
               }
           } 
       }
       return lista;
    }

    
    
    
}
