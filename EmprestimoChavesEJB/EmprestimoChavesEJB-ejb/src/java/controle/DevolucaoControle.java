/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.JPADAOXX;
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
    private dao.JPADAOXX conexao;

    public List<Emprestimo> getEmprestimos() {
        return  conexao.listarTodos(Emprestimo.class);
    }

    

    public Emprestimo getEmprestimo(Integer id) {
        return conexao.procurar(Emprestimo.class, id);
    }

    
    public List<Chave> getChaves(Autorizacao autorizacao) {        
        return conexao.listarTodos(Chave.class);
    }

    

    
    
    public boolean excluir(Emprestimo oEmprestimo) {
        conexao.excluir(oEmprestimo);
        return true;
    }

    
    public List<Emprestimo> devolucao(){        
        String[] fields = {"e.dataDevolucao"};
        String[] values = {"is null"};
        return  conexao.listar(Emprestimo.class, fields,values);
        
    }
    
    public boolean devolver(Emprestimo oEmprestimo){
      oEmprestimo.setDataDevolucao(new Date(System.currentTimeMillis()));
      Emprestimo emprestimo = conexao.procurar(Emprestimo.class, oEmprestimo.getId());
        try {
            conexao.salvar(oEmprestimo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Emprestimo buscarEmprestimo(int idEmprestimo) {
        try {
          return conexao.procurar(Emprestimo.class, idEmprestimo);
        } catch (Exception e) {
            return null;
        }
            
            
        
    }


    
}
