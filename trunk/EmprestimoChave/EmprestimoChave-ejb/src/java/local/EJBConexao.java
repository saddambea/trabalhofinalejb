/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local;

import java.util.List;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author dflenzi
 */
@Singleton
@Startup
public class EJBConexao {
    @PersistenceContext
    private EntityManager em;

    
    
    
    /*public <T> List<T> listarTodos(Class<T> classe) {
        Query cons = em.createQuery("select o from "+classe.getName()+" o");
        return cons.getResultList();
    }*/
    
}
