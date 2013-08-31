/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.ChaveDAO;
import dao.JPADAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Chave;

/**
 *
 * @author dflenzi
 */
@Stateless
public class ChaveControle{

    /**
     * Creates a new instance of ChaveControle
     */
    @EJB
    private JPADAO conexao;

    @EJB
    private ChaveDAO chaveDAO;

    public List<Chave> getChaves() {       
        return  conexao.listarTodos(Chave.class);
    }


    public Chave getChave(Integer id) throws Exception {
        Chave c = chaveDAO.carregar(id);
        return c;
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
