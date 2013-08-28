/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Query;
import modelo.Usuario;
import visao.AutenticacaoVisao;

/**
 *
 * @author dflenzi
 */
@ManagedBean
@SessionScoped
public class AutenticacaoAutorizacaoBean {

// referência para um objeto que representa
// o usuário autenticado
    private static Usuario usuario = new Usuario();
// indica se o usuário está ou não autorizado
    private static boolean autorizado = false;
    public String logoff(){
        usuario = new Usuario();
        autorizado=false;           
        return "principal";
    }
    public void login() {
        AutenticacaoVisao autenticacaovisao= new AutenticacaoVisao();
        if(!autenticacaovisao.login(usuario.getCodigo(), usuario.getSenha())){
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Usuário/Senha inválido","Não foi possível fazer login com o usuário/senha informados");
                             FacesContext.getCurrentInstance().addMessage("login", msg);
        }
    }
 
// gets e sets    /*** Creates a new instance of AutenticacaoAutorizacaoBean*/
    public AutenticacaoAutorizacaoBean() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isAutorizado() {
        return autorizado;
    }

    public void setAutorizado(boolean autorizado) {
        this.autorizado = autorizado;
    }
    
    public static void autenticar(Integer codigo, Integer senha){
        if (!getUsuarioAutenticacao(codigo, senha)){
          autorizado=false;  
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Usuário/Senha inválido","Não foi possível fazer login com o usuário/senha informados");
                             FacesContext.getCurrentInstance().addMessage("login", msg);
        }
        else{
            autorizado =true;
            usuario= getUsuario(codigo, senha);
            
        }
    }
    
    public static void autenticar(Integer codigo, Integer senha, String mensagem,String pagina){
        if (!getUsuarioAutenticacao(codigo, senha)){
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Usuário/Senha inválido",mensagem);
                             FacesContext.getCurrentInstance().addMessage(pagina, msg);
        }
    }
    
    
    public static Usuario getUsuario(Integer codigo, Integer senha){
        Query cons;
        //cons = JPADAO.getInstancia().getEM().createQuery("Select u from Usuario u Where u.codigo = :pcodigo and u.senha = :psenha");
        //cons.setParameter("pcodigo", codigo);
        //cons.setParameter("psenha", senha);
        try {
            Usuario user = null; 
            //Usuario user =  (Usuario) cons.getSingleResult();            
            if(user !=null)
                return user;
            else
               return null;
        } catch (Exception e) {
            return null;
       }
        
    }
    
    public static boolean getUsuarioAutenticacao(Integer codigo, Integer senha){
        Query cons;
        //cons = JPADAO.getInstancia().getEM().createQuery("Select u from Usuario u Where u.codigo = :pcodigo and u.senha = :psenha");
        //cons.setParameter("pcodigo", codigo);
        //cons.setParameter("psenha", senha);
        try {
             Usuario user = null; 
            //Usuario user =  (Usuario) cons.getSingleResult();            
            if(user !=null)
                return true;
            else
               return false;
        } catch (Exception e) {
            return false;
       }
    }
}
