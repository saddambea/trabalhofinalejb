package dao;

import com.sun.tools.jxc.gen.config.Classes;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Classe utilitária, segue o princípio do padrão DAO (data access object).
 * Permite a manipulação básica de quaisquer tipos de entidades.
 * @author Fernando dos Santos
 */
@Stateless
public class JPADAO {

   
    @PersistenceContext
    private EntityManager em;
    

     /**
     * Salva uma entidade.
     * Este método utiliza a operação merge() da unidade de persistência. 
     * Logo, pode-se passar entidades em estado NEW, MANAGED, ou DETACHED.
     * @param entidade a entidade a ser salva.
     */
    public void salvar(Object entidade) {
        em.getTransaction().begin();
        em.merge(entidade);
        em.getTransaction().commit();
    }

    /**
     * Procura uma entidade pela sua chave primária.
     * Este método utiliza a operação find() da unidade de persistência.
     * @param <T> O tipo da classe da entidade procurada, é extraido automaticamente pelo Java.
     * @param classe A classe da entidade procurada.
     * @param chave O valor da chave procurada.
     * @return O objeto de tipo T procurado, ou null caso não encontre.
     */
    public <T> T procurar(Class<T> classe, Object chave) {
        return em.find(classe, chave);
    }

    /**
     * Recarrega a entidade.
     * Este método utiliza a operação refresh() da unidade de persistência.
     * @param entidade A entidade a ser recarregada.
     */
    public void recarregar(Object entidade) {
        em.refresh(entidade);
    }

    /**
     * Remove a entidade.
     * Este método utiliza a operação remove() da unidade de persistência.
     * @param entidade A entidade a ser removida.
     */
    public void excluir(Object entidade) {
        em.getTransaction().begin();
        em.remove(em.merge(entidade));
        em.getTransaction().commit();
    }

    /**
     * Retorna uma lista com todas as entidades.
     * @param <T> O tipo da classe da entidade procurada, é extraido automaticamente pelo Java.
     * @param classe A classe da entidade procurada.
     * @return Lista com todas as entidades encontradas no banco.
     */
    public <T> List<T> listarTodos(Class<T> classe) {
        Query cons = em.createQuery("select o from "+classe.getName()+" o");
        return cons.getResultList();
    }
    
    /**
     * Retorna o EntityManager utilizado por este DAO.
     * Este método pode ser utilizado para criação de consultas a partir do entity manager.
     * @return O EntityManager utilizado por este DAO.
     */
    public EntityManager getEM(){
        return em;
    }
    
    public <T> Object buscar(Class<T> classe, String[] fields, String[] values){
        String  sqlWhere = "";
        
        Query cons = em.createQuery("Select o from " + classe.getName() + sqlWhere) ;        
        for(int i = 0; i<fields.length -1; i++){
            if(sqlWhere.isEmpty()){
                sqlWhere = "Where " + fields[i] + " :"+fields[i]; 
            }
            else{
                sqlWhere = " and " + fields[i] + " :"+fields[i]; 
            }
            cons.setParameter(fields[i], values[i]);
        }
        
        return  cons.getSingleResult();    
    }
}
