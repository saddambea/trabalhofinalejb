/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import controle.UsuarioControle;
import javax.faces.bean.ManagedBean;
import modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Autorizacao;

/**
 *
 * @author dflenzi
 */
@ManagedBean
@SessionScoped
public class UsuarioBean {

    /**
     * Creates a new instance of UsuarioBean
     */
    private Usuario usuario = new Usuario();    
    private Usuario usuarioLogado = new Usuario();
    private static List<Usuario> usuarios = new ArrayList<Usuario>();
    private static int usuarioId = 1;
    private boolean salvo = false;
    private String mensagem;
    private List<Autorizacao> autorizacoes = new ArrayList<Autorizacao>();
    @EJB
    private UsuarioControle usuariocontrole;
    
    

    public boolean isSalvo() {
        return salvo;
    }

    public void setSalvo(boolean salvo) {
        this.salvo = salvo;
    }

    public UsuarioBean() {
    }

    public List<Usuario> getUsuarios() throws Exception{        
        return usuariocontrole.getUsuarios();
    }

    public void setUsuarios(List<Usuario> usuarios) {
        UsuarioBean.usuarios = usuarios;
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

    public String editar(Usuario oUsuario) throws Exception{     
        
        this.usuario = oUsuario;
        salvo = false;
        return "usuariocad";
    }


     public String excluir() {
         try {
             usuariocontrole.excluir(usuariocontrole.buscarUsuario(usuario.getCodigo()));
         } catch (Exception e) {
             
         }
          
          
        return "usuariolist";
    }

    public String excluir(Usuario oUsuario) {
        usuariocontrole.excluir(oUsuario);
        return "usuariolist";
    }

    public String salvar() {
        try {
            usuariocontrole.salvar(usuario);
            this.salvo=true;            
            
        } catch (Exception e) {
            this.salvo=false;
        }
        return "usuariolist";
    }

    public String cancelar() {
        this.salvo = false;
        return "principal";
    }
    
    public String manterAutorizacao(Usuario oUsuario){
        if (oUsuario !=null && oUsuario != this.usuario){
            this.usuario = oUsuario;
        }
        
        return "manutencaousuariolist";
    }
    
    public List<Autorizacao> getAutorizacoes() throws Exception{               
        autorizacoes = usuariocontrole.getAutorizacoes(usuario);
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
        try {
            return usuariocontrole.getUsuarioByCodigo(codigo);
        } catch (Exception e) {
            
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Usuário " + codigo + " não cadastrado.","Não foi possível encontrar o usuário.");
                             FacesContext.getCurrentInstance().addMessage("Consulta", msg);
          return new Usuario();                   

            
        }
        
    }
    
    
    
}
