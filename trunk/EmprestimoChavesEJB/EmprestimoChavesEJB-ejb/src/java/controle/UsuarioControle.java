/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.AutorizacaoDAO;
import dao.UsuarioDAO;
import java.util.ArrayList;
import modelo.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import modelo.Autorizacao;


@Stateful
public class UsuarioControle{

    /**
     * Creates a new instance of UsuarioControle
     */
    @EJB
    private dao.JPADAO conexao;
    
    @EJB
    private UsuarioDAO usuarioDAO;
    
    @EJB
    private AutorizacaoDAO autorizacaoDAO;
    

    public List<Usuario> getUsuarios() throws Exception {
        //return conexao.listarTodos(Usuario.class);
        return usuarioDAO.listarTodos();
    }

    public Boolean excluir(Usuario usuario) {
        try {
            usuarioDAO.excluir(usuario);
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }

    public boolean salvar(Usuario usuario) {
        try {
            usuarioDAO.salvar(usuario);
            return true;
        } catch (Exception e) {
            return false;
        }
  
        
    }

    public Usuario buscarUsuario(int idUsuario) throws Exception{
        //return conexao.procurar(Usuario.class, idUsuario);
        return usuarioDAO.carregar(idUsuario);
    }

   
    
    public Usuario getUsuarioByCodigo(int codigo){        
        //return conexao.buscarSimples(Usuario.class, "codigo", codigo);
        return usuarioDAO.buscarSimples(codigo);
    }

    public List<Autorizacao> getAutorizacoes(Usuario usuario) throws Exception{
       List<Autorizacao> lista = new ArrayList<Autorizacao>();
       List<Autorizacao> todas =  new ArrayList<Autorizacao>();
       //todas = conexao.listarTodos(Autorizacao.class);
       todas = autorizacaoDAO.listarTodos();
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
