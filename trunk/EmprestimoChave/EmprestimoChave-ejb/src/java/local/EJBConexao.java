/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author dflenzi
 */
@Singleton
@Startup
public class EJBConexao {
    @PersistenceContext
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
}
