/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package remote;

import java.util.List;
import javax.ejb.Remote;
import modelo.Autorizacao;
import modelo.Usuario;

/**
 *
 * @author abaehr
 */
@Remote
public interface UsuarioRemote {
    boolean isSalvo();
    void setSalvo(boolean salvo);
    List<Usuario> getUsuarios();
    void setUsuarios(List<Usuario> usuarios);
    Usuario getUsuario();
    void setUsuario(Usuario usuario);
    String novo();
    String editar(Usuario oUsuario);
    String excluir(Usuario oUsuario);
    String salvar();
    String cancelar();
    String login();
    Usuario buscarUsuario(int idUsuario);
    String manterAutorizacao(Usuario oUsuario);
    List<Autorizacao> getAutorizacoes();
    void setAutorizacoes(List<Autorizacao> autorizacoes);
    Usuario getUsuarioLogado();
    Usuario getUsuarioByCodigo(int codigo);
}
