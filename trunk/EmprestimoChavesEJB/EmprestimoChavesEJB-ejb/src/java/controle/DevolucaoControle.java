/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.ChaveDAO;
import dao.DevolucaoDAO;
import dao.EmprestimoDAO;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Autorizacao;
import modelo.Emprestimo;
import modelo.Chave;

/**
 *
 * @author dflenzi
 */
@Stateless
public class DevolucaoControle{

    /**
     * Creates a new instance of EmprestimoBean
     */
    @EJB
    private DevolucaoDAO devolucaoDAO;
    
    @EJB
    private ChaveDAO chaveDAO;
    
    @EJB
    private EmprestimoDAO emprestimoDAO;

    public List<Emprestimo> getEmprestimos() {
        try {
            return  devolucaoDAO.listarTodos();
        } catch (Exception e) {
            return null;
        }
        
    }

    public Emprestimo getEmprestimo(Integer id) {
        try {
            return devolucaoDAO.carregar(id);
        } catch (Exception e) {
            return null;
        }
        
    }

    
    public List<Chave> getChaves(Autorizacao autorizacao) {        
        try {
            return chaveDAO.listarTodos();
        } catch (Exception e) {
            return null;
        }
        
    }

    
    public boolean excluir(Emprestimo oEmprestimo) {
        try {
            devolucaoDAO.excluir(oEmprestimo);
            return true;
        } catch (Exception e) {
            return false;
        }
        
        
    }
    
    public List<Emprestimo> devolucao(){        
        
        try {
            return  emprestimoDAO.listarTodosAtivos();
        } catch (Exception e) {
            return null;
        }
        
        
    }
    
    public boolean devolver(Emprestimo oEmprestimo){
        try {
            Emprestimo emprestimo = devolucaoDAO.carregar(oEmprestimo.getId());
            emprestimo.setDataDevolucao(new Date(System.currentTimeMillis()));
            //emprestimo.setChave(chaveDAO.carregar(emprestimo.getChave().getId()));
            devolucaoDAO.salvar(emprestimo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Emprestimo buscarEmprestimo(int idEmprestimo) {
        try {
          return emprestimoDAO.carregar(idEmprestimo);
        } catch (Exception e) {
            return null;
        }
            
            
        
    }


    
}
