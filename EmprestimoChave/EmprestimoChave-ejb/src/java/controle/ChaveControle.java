/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.JPADAO;
import javax.faces.bean.ManagedBean;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.persistence.Query;
import modelo.Chave;

/**
 *
 * @author dflenzi
 */
@ManagedBean
@SessionScoped
@Stateless
public class ChaveControle {

    /**
     * Creates a new instance of ChaveControle
     */
    @EJB
    private JPADAO conexao;



    public List<Chave> getChaves() {
        return  conexao.listarTodos(Chave.class);
    }


    public Chave getChave(Integer id) {
        return conexao.procurar(Chave.class, id);
    }

    public Boolean excluir(Chave chave) {
        conexao.excluir(chave);
        return true;
        
    }

    public boolean salvar(Chave chave) {
        conexao.salvar(chave);
        return true;
    }



    
}
