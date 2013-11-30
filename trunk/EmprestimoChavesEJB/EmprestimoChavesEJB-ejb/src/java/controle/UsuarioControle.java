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
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import modelo.Autorizacao;
import static simetrico.CifraDESede.resumo_chave;


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

    public SecretKey getCriptografia(String cripto){
            SecretKey secretKey = null;
            try{
                byte key[] = resumo_chave(cripto);
                DESedeKeySpec desKeySpec = new DESedeKeySpec(key);
                SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
                secretKey = keyFactory.generateSecret(desKeySpec); 
            }catch(Exception e){
                System.out.println("Lixo");
            }
            
            
            return secretKey;
    }
    
    public boolean salvar(Usuario usuario) {
        try {
            usuario.setEmail(new String(getCriptografia(usuario.getEmail()).getEncoded()));
            usuarioDAO.salvar(usuario);
            return true;
        } catch (Exception e) {
            return false;
        }
  
        
    }

    public Usuario buscarUsuario(int idUsuario) throws Exception{
        return usuarioDAO.carregar(idUsuario);
    }

   
    
    public Usuario getUsuarioByCodigo(int codigo){        
        return usuarioDAO.buscarSimples(codigo);
    }
    
    public Usuario getUsuarioAdministrador(){
        try {
            List<Usuario> lista = usuarioDAO.listarTodos();
            for(Usuario usu : lista){
                if(usu.getTipo().getPermUsuario() ||
                   usu.getTipo().getDescricao().equals("Administrador")){
                    return usu;
                }
            }
            
        } catch (Exception e) {
            return null;
        }
        
        return null;
    }
    public List<Autorizacao> getAutorizacoes(Usuario usuario) throws Exception{
       List<Autorizacao> lista = new ArrayList<Autorizacao>();
       List<Autorizacao> todas =  new ArrayList<Autorizacao>();
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
