/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Chave;

/**
 *
 * @author abaehr
 */
public class ChaveDAO extends DAOBase<Chave> {

    public ChaveDAO() {
        super(Chave.class);
    }

    @Override
    public List<Chave> listarTodos() throws Exception {
        return super.listarTodos(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
