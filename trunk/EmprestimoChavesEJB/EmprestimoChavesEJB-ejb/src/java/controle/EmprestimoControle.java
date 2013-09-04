/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.ArrayList;
import dao.AutorizacaoDAO;
import dao.ChaveDAO;
import dao.EmprestimoDAO;
import dao.UsuarioDAO;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Emprestimo;
import modelo.Usuario;
import modelo.Chave;

/**
 *
 * @author dflenzi
 */
@Stateless
public class EmprestimoControle {

    @EJB
    private AutenticacaoControle autenticacao;

    @EJB
    private AutorizacaoDAO autDAO;
    
    @EJB
    ChaveDAO chavesDAO;
    
    @EJB
    EmprestimoDAO emprestimoDAO;
    
    @EJB
    UsuarioDAO usuarioDAO;
    
    /**
     * Creates a new instance of EmprestimoControle
     */
    public EmprestimoControle() {
        super();
    }

    public List<Emprestimo> getEmprestimos() {
        try {
        return emprestimoDAO.listarTodos();    
        } catch (Exception e) {
          return null;
        }
    }
        
    
    
    public List<Emprestimo> getEmprestimosAtivos(){
        try {
            return emprestimoDAO.listarTodosAtivos();
        } catch (Exception e) {
            return null;
        }
       
       }

    public List<Chave> getChaves() {
        try {
          return  chavesDAO.listarTodos();
        } catch (Exception e) {
            return null;
        }
        
        
    }

    public boolean excluir(Emprestimo oEmprestimo) {
        try {
            emprestimoDAO.excluir(oEmprestimo);
            return true;
        } catch (Exception e) {
            return false;
        }


    }

    public List<Chave> buscarChaves(Usuario oUsuario){
        try {
            return autDAO.listarChavesUsuario(oUsuario, new Date(System.currentTimeMillis()));
        } catch (Exception e) {
            return null;
        }
      
    }

    public boolean emprestar(Usuario oBalconista, Usuario usuario, Integer senha, List<Chave> chaves) throws Exception{
        Emprestimo emp;
        String[] fields = {"codigo"};
        String[] values = {String.valueOf(senha)};
        Usuario usuarioBusca;
        try {
            usuarioBusca = usuarioDAO.buscar(fields, values);
        } catch (Exception e) {
            usuarioBusca = null;
        }


        if (!autenticacao.getUsuarioAutenticacao(usuario.getCodigo(), senha)) {
            return false;

        } else {

            if (chaves.size() > 0) {
                for (Chave cha : chaves) {
                    //
                    emp = new Emprestimo();
                    try {
                        emp.setBalconista(usuarioDAO.carregar(oBalconista.getId()));
                    } catch (Exception e) {
                    }
                        
                    emp.setUsuario(usuarioDAO.buscarSimples(usuario.getCodigo()));
                    emp.setChave(chavesDAO.carregar(cha.getId()));
                    emp.setDataEmprestimo(new Date(System.currentTimeMillis()));
                    emprestimoDAO.salvar(emp);
                }

            }
            return true;
        }

    }

    public Emprestimo buscarEmprestimo(int idEmprestimo) {
        try {
            return emprestimoDAO.carregar(idEmprestimo);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean salvar(Emprestimo oEmprestimo) {
        try {
            
            emprestimoDAO.salvar(oEmprestimo);
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
