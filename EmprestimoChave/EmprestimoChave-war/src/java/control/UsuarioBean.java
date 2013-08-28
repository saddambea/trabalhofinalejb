/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.JPADAO;
import javax.faces.bean.ManagedBean;
import modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Query;
import modelo.Autorizacao;
import modelo.TipoUsuario;

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
    
    

    public boolean isSalvo() {
        return salvo;
    }

    public void setSalvo(boolean salvo) {
        this.salvo = salvo;
    }

    public UsuarioBean() {
    }

    public List<Usuario> getUsuarios() {
        //usuarios = JPADAO.getInstancia().listarTodos(Usuario.class);
        return usuarios;
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

    public String editar(Usuario oUsuario) {     
        
        //this.usuario = JPADAO.getInstancia().procurar(Usuario.class, oUsuario.getId());
        salvo = false;
        return "usuariocad";
    }

    public String excluir(Usuario oUsuario) {
        //JPADAO.getInstancia().excluir(oUsuario);
        return "usuariolist";
    }

    public String excluir() {
        usuarios.remove(usuario);
        return "usuariolist";
    }

    public String salvar() {
        try {
            //JPADAO.getInstancia().salvar(usuario);
            this.salvo=true;
            this.usuario = FuncoesGeraisBean.getUsuarioByCodigo(usuario.getCodigo());
            
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
      return "";  

    }

    public static Usuario buscarUsuario(int idUsuario) {
        return null;//JPADAO.getInstancia().procurar(Usuario.class, idUsuario);
    }

    public String manterAutorizacao(Usuario oUsuario){
        if (oUsuario !=null && oUsuario != this.usuario){
            this.usuario = oUsuario;
        }
        
        return "manutencaousuariolist";
    }
    
    public List<Autorizacao> getAutorizacoes() {        
        Query cons = null;//JPADAO.getInstancia().getEM().createQuery("Select a from Autorizacao a where a.usuario.id = :pid" );
        
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
        Query cons = null;//JPADAO.getInstancia().getEM().createQuery("Select u from Usuario u where u.codigo = :pcodigo");        
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
