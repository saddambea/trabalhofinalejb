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
    private Usuario usuario = new Usuario();    
    private Usuario usuarioLogado = new Usuario();
    private static List<Usuario> usuarios = new ArrayList<Usuario>();
    private static int usuarioId = 1;
    private boolean salvo = false;
    private String mensagem;
    private List<Autorizacao> autorizacoes = new ArrayList<Autorizacao>();
    
    @EJB
    private JPADAO conexao;
    

    public boolean isSalvo() {
        return salvo;
    }

    public void setSalvo(boolean salvo) {
        this.salvo = salvo;
    }

    public UsuarioControle() {
    }

    public List<Usuario> getUsuarios() {
        usuarios = conexao.listarTodos(Usuario.class);
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        UsuarioControle.usuarios = usuarios;
    }

    public Usuario getUsuario() {        
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String novo() {
        this.usuario = new Usuario();
        this.salvo = false;
        return "usuariocad";
    }

    public String editar(Usuario oUsuario) {     
        
        this.usuario = conexao.procurar(Usuario.class, oUsuario.getId());
        salvo = false;
        return "usuariocad";
    }

    public String excluir(Usuario oUsuario) {
        conexao.excluir(oUsuario);
        return "usuariolist";
    }

    public String excluir() {
        usuarios.remove(usuario);
        return "usuariolist";
    }

    public String salvar() {
        try {
            conexao.salvar(usuario);
            this.salvo=true;
            this.usuario = FuncoesGeraisControle.getUsuarioByCodigo(usuario.getCodigo());
            
        } catch (Exception e) {
            this.salvo=false;
        }
        return "usuariolist";
    }

    public String cancelar() {
        this.salvo = false;
        return "principal";
    }
    
    public String login(){
        if(usuario==null)
        {    
          this.novo();
        }        
        
        if (conexao.listarTodos(Usuario.class).isEmpty()){
            //Adicionar automaticamente os tipos de 
            TipoUsuario tipoA = new TipoUsuario("Administrador", true, true, true, true, true, true);
            conexao.salvar(tipoA);
            TipoUsuario tipoB = new TipoUsuario("Balconista", false, true, true, true, true, true);
            conexao.salvar(tipoB);
            TipoUsuario tipoU = new TipoUsuario("Usuário", false, false, false, false, false, true);
            conexao.salvar(tipoU);
            
            //Adicionar automaticamente usuarios
            
            
            if(conexao.listarTodos(Usuario.class).isEmpty()){
                Usuario usuario =  new Usuario(111111, "Administrador", "administrador@adm.com.br", 123, tipoA);
                conexao.salvar(usuario);
                
                usuario =  new Usuario(222222, "Balconista", "balconista@furb.com.br", 456, tipoB);
                conexao.salvar(usuario);
                
                usuario =  new Usuario(333333, "Usuario1", "usuario@furb.com.br", 789, tipoU);
                conexao.salvar(usuario);
            }
        }
        
        cons = JPADAO.getInstancia().getEM().createQuery("select u from Usuario u where u.codigo = :pcodigo and u.senha = :psenha");
        cons.setParameter("pcodigo", usuario.getCodigo());
        cons.setParameter("psenha", usuario.getSenha());
        
        try {
            Usuario usu = (Usuario) cons.getSingleResult();    
            //salvo o usuario logado
            if(usu!=null){
                if (usuarioLogado != usu){
                    usuarioLogado = usu;
                }
                
              return "principal";
            }          
            else{
              this.mensagem = "Usuário/Senha inválidos";  
              return "login";
            }  
            
        } catch (Exception e) {
              this.mensagem = "Usuário/Senha inválidos";  
              return "login";
        }
    }

    public static Usuario buscarUsuario(int idUsuario) {
        return conexao.procurar(Usuario.class, idUsuario);
    }

    public String manterAutorizacao(Usuario oUsuario){
        if (oUsuario !=null && oUsuario != this.usuario){
            this.usuario = oUsuario;
        }
        
        return "manutencaousuariolist";
    }
    
    public List<Autorizacao> getAutorizacoes() {        
        Query cons = JPADAO.getInstancia().getEM().createQuery("Select a from Autorizacao a where a.usuario.id = :pid" );
        
        cons.setParameter("pid", this.usuario.getId());
        autorizacoes = cons.getResultList();
        return autorizacoes;
    }

    public void setAutorizacoes(List<Autorizacao> autorizacoes) {
        this.autorizacoes = autorizacoes;
    }
    

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
   
    
    public Usuario getUsuarioByCodigo(int codigo){        
        Query cons = JPADAO.getInstancia().getEM().createQuery("Select u from Usuario u where u.codigo = :pcodigo");        
        cons.setParameter("pcodigo", codigo);
        try {
            return (Usuario) cons.getSingleResult();
        } catch (Exception e) {
            
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Usuário " + codigo + " não cadastrado.","Não foi possível encontrar o usuário.");
                             FacesContext.getCurrentInstance().addMessage("Consulta", msg);
          return new Usuario();                   

            
        }
        
    }
    
    
    
}
