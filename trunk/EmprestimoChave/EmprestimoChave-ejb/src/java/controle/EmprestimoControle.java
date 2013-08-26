/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.JPADAO;
import javax.faces.bean.ManagedBean;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.persistence.Query;
import modelo.Emprestimo;
import modelo.Usuario;
import modelo.Chave;

/**
 *
 * @author dflenzi
 */
@ManagedBean
@SessionScoped
@Stateless
public class EmprestimoControle {
    @EJB    
    private JPADAO conexao;
    
    @EJB
    private AutenticacaoControle autenticacao;
    /**
     * Creates a new instance of EmprestimoControle
     */
    public EmprestimoControle() {
        super();
    }

    public List<Emprestimo> getEmprestimos() {
        return  conexao.listarTodos(Emprestimo.class);
    }


    public List<Chave> getChaves() {        
        return conexao.listarTodos(Chave.class);
    }

    
    public boolean excluir(Emprestimo oEmprestimo) {
        try {
            conexao.excluir(oEmprestimo);
            return true;
        } catch (Exception e) {
            return false;
        }
        
        
    }


    
    public List<Chave> buscarChaves(Usuario oUsuario){
      Query cons;        
      
      cons = conexao.getEM().createQuery("Select a.chave From Autorizacao a where " +
                                          " a.usuario.codigo = :pcodigo and (a.dataFim is null or a.dataFim > :pdata) "
                                         + " and not exists (Select e From Emprestimo e Where e.chave = a.chave and e.dataDevolucao is null) ");      
      cons.setParameter("pcodigo", oUsuario.getCodigo());
      cons.setParameter("pdata", new Date(System.currentTimeMillis()));
      
      return cons.getResultList();
    }
    
    public boolean emprestar(Usuario oBalconista, Usuario usuario,Integer senha, List<Chave> chaves) {
        Emprestimo emp;
        String[]fields = {"codigo"};
        String[] values = {String.valueOf(senha)};
        Usuario usuarioBusca;
        try {
            usuarioBusca =  conexao.buscar(Usuario.class, fields, values);
        } catch (Exception e) {
            usuarioBusca = null;
        }
        
        
        if (!autenticacao.getUsuarioAutenticacao(usuario.getCodigo(), senha)){
          return false;
            
        }
        else
        {
            
            if (chaves.size() > 0){                                
                for(Chave cha : chaves ){
                        //
                    emp = new  Emprestimo();
                    emp.setBalconista(conexao.procurar(Usuario.class, oBalconista.getId()));
                    emp.setUsuario(usuario);
                    emp.setChave(cha);
                    emp.setDataEmprestimo(new Date(System.currentTimeMillis()));                       
                }
                
            }
            return true;
        }
            
    }
    
    public Emprestimo buscarEmprestimo(int idEmprestimo) {
        try {
          return conexao.procurar(Emprestimo.class, idEmprestimo);
        } catch (Exception e) {
            return null;
        }
    }

    
}
