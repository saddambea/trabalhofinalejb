/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import modelo.Chave;
import modelo.Emprestimo;

/**
 *
 * @author abaehr
 */
@Stateless
public class EmprestimoDAO extends DAOBase<Emprestimo> {

    public EmprestimoDAO() {
        super(Emprestimo.class);
    }

    @Override
    public List<Emprestimo> listarTodos() throws Exception {
        return super.listarTodos(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Emprestimo> listarTodosAtivos() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        return dao.listarNamedQuery("emprestimos.listarativos", params);

    }
    
    public List<Chave> listarChavesAtivas() throws Exception {
          Map<String, Object> params = new HashMap<String, Object>();
        return dao.listarNamedQuery("emprestimos.listarchave", params);
    }
    
    
    
}
