/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.JPADAO;
import dao.TipoUsuarioDAO;
import dao.UsuarioDAO;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.TipoUsuario;
import modelo.Usuario;

@Stateless
public class AutenticacaoControle{

// referência para um objeto que representa
// o usuário autenticado
    @EJB
    private JPADAO conexao;
    @EJB
    private UsuarioDAO usuarioDAO;
    @EJB
    private TipoUsuarioDAO tipoUsuarioDAO;

    public AutenticacaoControle() {
        System.out.println("Iniciou o autenticacaoControle");
    }

    
    
    public boolean login(Integer codigo, Integer senha) throws Exception{
        
        if(usuarioDAO.listarTodos().isEmpty()){
            //Adicionar automaticamente os tipos de 
            TipoUsuario tipoA = new TipoUsuario("Administrador", true, true, true, false, false, true);
            tipoUsuarioDAO.salvar(tipoA);
            TipoUsuario tipoB = new TipoUsuario("Balconista", false, false, false, true, true, true);
            tipoUsuarioDAO.salvar(tipoB);
            TipoUsuario tipoU = new TipoUsuario("Usuário", false, false, false, false, false, true);
            tipoUsuarioDAO.salvar(tipoU); 
            //Adicionar automaticamente usuarios

            Usuario usuario =  new Usuario(111111, "Administrador", "administrador@adm.com.br", 111111, tipoA);
            usuarioDAO.salvar(usuario);

            usuario =  new Usuario(222222, "Balconista", "balconista@furb.com.br", 222222, tipoB);
            usuarioDAO.salvar(usuario);

            usuario =  new Usuario(333333, "Usuario", "usuario@furb.com.br", 333333, tipoU);
            usuarioDAO.salvar(usuario);
            
        }
        
        return autenticar(codigo, senha);
    }
 
// gets e sets    /*** Creates a new instance of AutenticacaoControle*/
    public boolean autenticar(Integer codigo, Integer senha){
        return getUsuarioAutenticacao(codigo, senha);
    }
    
    public boolean autenticar(Integer codigo, Integer senha, String mensagem,String pagina){
        return getUsuarioAutenticacao(codigo, senha);
    }
    
    
    public Usuario getUsuario(Integer codigo, Integer senha) throws Exception{        
        String[] fields = {"codigo", "senha"};
        String[] values = {String.valueOf(codigo), String.valueOf(senha)}; 
        
        Usuario user = usuarioDAO.buscar(fields, values);                                      
                                      
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
            //Usuario user = conexao.buscarSimples(Usuario.class, "codigo", senha);            
            Usuario user = usuarioDAO.buscarSimples(codigo);
            
            if(user !=null)
                return (user.getSenha()==senha);
            else
               return false;
        } catch (Exception e) {
            return false;
       }
    }
}
