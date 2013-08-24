/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.servlet.http.HttpServlet;
import modelo.Autorizacao;
import modelo.Usuario;
import remote.UsuarioRemote;

/**
 *
 * @author abaehr
 */
@Stateful
public class UsuarioLocal implements UsuarioRemote{
    
    private Usuario usuario = new Usuario();    
    private Usuario usuarioLogado = new Usuario();
    private static List<Usuario> usuarios = new ArrayList<Usuario>();
    private static int usuarioId = 1;
    private boolean salvo = false;
    private String mensagem;
    private List<Autorizacao> autorizacoes = new ArrayList<Autorizacao>();
   
    @EJB
    private EJBConexao instancia;
    
    @Override
    public boolean isSalvo() {
        return salvo;
    }

    @Override
    public void setSalvo(boolean salvo) {
        this.salvo = salvo;
    }

    @Override
    public List<Usuario> getUsuarios() {
        usuarios = instancia.listarTodos(Usuario.class);
        return usuarios;
    }        

    @Override
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public void setUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String novo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String editar(Usuario oUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String excluir(Usuario oUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String salvar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String cancelar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String login() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario buscarUsuario(int idUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String manterAutorizacao(Usuario oUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Autorizacao> getAutorizacoes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAutorizacoes(List<Autorizacao> autorizacoes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario getUsuarioLogado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario getUsuarioByCodigo(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
