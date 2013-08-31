/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.JPADAOXX;
import java.io.Serializable;
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
    private dao.JPADAOXX conexao;



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
