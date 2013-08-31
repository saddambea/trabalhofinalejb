/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.ejb.Stateless;
import modelo.Emprestimo;

/**
 *
 * @author abaehr
 */
@Stateless
public class DevolucaoDAO extends DAOBase<Emprestimo> {

    public DevolucaoDAO() {
        super(Emprestimo.class);
    }

    @Override
    public List<Emprestimo> listarTodos() throws Exception {
        return super.listarTodos(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
