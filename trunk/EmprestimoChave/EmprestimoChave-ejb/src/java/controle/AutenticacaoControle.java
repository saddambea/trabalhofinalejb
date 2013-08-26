/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.JPADAO;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Query;
import modelo.TipoUsuario;
import modelo.Usuario;
/**
 *
 * @author dflenzi
 */
@ManagedBean
@SessionScoped
public class AutenticacaoControle {

// referência para um objeto que representa
// o usuário autenticado
    @EJB
    private JPADAO conexao;
    
    public boolean login(Integer codigo, Integer senha) {
        
        Query cons  = conexao.getEM().createQuery("Select u from Usuario u");

        if (cons.getResultList().isEmpty()){
            //Adicionar automaticamente os tipos de 
            TipoUsuario tipoA = new TipoUsuario("Administrador", true, true, true, false, false, true);
            TipoUsuario tipoB = new TipoUsuario("Balconista", false, false, false, true, true, true);
            TipoUsuario tipoU = new TipoUsuario("Usuário", false, false, false, false, false, true);
            
            //Adicionar automaticamente usuarios
            
            if(conexao.listarTodos(Usuario.class).isEmpty()){
                Usuario usuario =  new Usuario(111111, "Administrador", "administrador@adm.com.br", 111111, tipoA);
                conexao.salvar(usuario);
                
                usuario =  new Usuario(222222, "Balconista", "balconista@furb.com.br", 222222, tipoB);
                conexao.salvar(usuario);
                
                usuario =  new Usuario(333333, "Usuario", "usuario@furb.com.br", 333333, tipoU);
                conexao.salvar(usuario);
            }
        }
        
        return autenticar(codigo, senha);
    }
 
// gets e sets    /*** Creates a new instance of AutenticacaoControle*/
    public AutenticacaoControle() {
    }

    public boolean autenticar(Integer codigo, Integer senha){
        return getUsuarioAutenticacao(codigo, senha);
    }
    
    public boolean autenticar(Integer codigo, Integer senha, String mensagem,String pagina){
        return getUsuarioAutenticacao(codigo, senha);
    }
    
    
    public Usuario getUsuario(Integer codigo, Integer senha){
        Query cons;
        
        String[] fields = {"codigo", "senha"};
        String[] values = {String.valueOf(codigo), String.valueOf(senha)}; 
        
        Usuario user = conexao.buscar(Usuario.class,fields, values);                                      
                                      
        try {
                        
            if(user !=null)
                return user;
            else
               return null;
        } catch (Exception e) {
            return null;
       }
        
    }
    
    public boolean getUsuarioAutenticacao(Integer codigo, Integer senha){

        String[] fields = {"codigo", "senha"};
        String[] values = {String.valueOf(codigo), String.valueOf(senha)}; 
        
                                             
        try {
            Usuario user = conexao.buscar(Usuario.class,fields, values);            
            if(user !=null)
                return true;
            else
               return false;
        } catch (Exception e) {
            return false;
       }
    }
}
