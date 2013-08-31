/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author abaehr
 */
public abstract class DAOBase<T> {
    
    @EJB
    protected JPADAO dao;
    private final Class clazz;
    
    public DAOBase(Class clazz) {
        this.clazz = clazz;
    }
    
    public void salvar(T obj) throws Exception {
    
        dao.salvar(obj);
        
    }
    
    public T carregar(Integer id) throws Exception {
        return (T) dao.procurar(clazz, id);
    }
    
    public List<T> listarTodos() throws Exception {
        return dao.listarTodos(clazz);
    }
    
    public void excluir(T obj) throws Exception{
        dao.excluir(obj);
    }
    
}
