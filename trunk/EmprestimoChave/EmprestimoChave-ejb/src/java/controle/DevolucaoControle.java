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
public class DevolucaoControle {

    /**
     * Creates a new instance of EmprestimoBean
     */
    @EJB
    private JPADAO conexao;

    public List<Emprestimo> getEmprestimos() {
        return  conexao.listarTodos(Emprestimo.class);
    }

    

    public Emprestimo getEmprestimo(Integer id) {
        return conexao.procurar(Emprestimo.class, id);
    }

    
    public List<Chave> getChaves() {        
        return conexao.buscar(Emprestimo.class, 
                              new String[] = {""}, 
                              new String[] = {""});
    }

    

    
    
    public boolean excluir(Emprestimo oEmprestimo) {
        conexao.excluir(oEmprestimo);
        return true;
    }

    public String excluir() {        
        JPADAO.getInstancia().excluir(emprestimo);
        
        return "devolucaolist";
    }

    
    public String devolucao(){                
        Query cons = JPADAO.getInstancia().getEM().createQuery("Select e from Emprestimo e where e.dataDevolucao is null");
        this.salvo = false;
        emprestimos = cons.getResultList();
        return "devolucaoconsulta";
    }
    
    public String devolver(Emprestimo oEmprestimo){
      oEmprestimo.setDataDevolucao(new Date(System.currentTimeMillis()));
      emprestimo = JPADAO.getInstancia().procurar(Emprestimo.class, oEmprestimo.getId());
        try {
            JPADAO.getInstancia().salvar(oEmprestimo);
            this.salvo = true;
        } catch (Exception e) {
            this.salvo = false;
        }
      
      return "devolucaoconsulta";
    }
    public String salvar() {
        System.out.println("Devolução salva: " + emprestimo.getId());
        JPADAO.getInstancia().salvar(emprestimo);
        this.salvo=true;
        
        return "devolucaocad";
    }

    public String cancelar() {
        this.salvo = false;
        return "devolucaoconsulta";
    }

    public static Emprestimo buscarEmprestimo(int idEmprestimo) {
        for (Emprestimo cat : emprestimos) {
            if (cat.getId() == idEmprestimo) {
                return cat;
            }
        }
        return null;
    }

    public String emprestimo(){
        this.emprestimo = new Emprestimo();
        this.salvo=false;
        this.chaves.clear();
        return "emprestimoconsulta";
    }
    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public static int getEmprestimoId() {
        return emprestimoId;
    }

    public static void setEmprestimoId(int emprestimoId) {
        DevolucaoControle.emprestimoId = emprestimoId;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public static List<Emprestimo> getDevolucoes() {
        return devolucoes;
    }

    public static void setDevolucoes(List<Emprestimo> devolucoes) {
        DevolucaoControle.devolucoes = devolucoes;
    }

    
}
