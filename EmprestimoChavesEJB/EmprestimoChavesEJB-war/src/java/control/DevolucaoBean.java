/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;


import controle.EmprestimoControle;
import javax.faces.bean.ManagedBean;

import java.util.ArrayList;
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
public class DevolucaoBean {

    /**
     * Creates a new instance of EmprestimoBean
     */
    private Emprestimo emprestimo;
    
    private Usuario usuario = new Usuario();
    private static List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
    private static List<Emprestimo> devolucoes = new ArrayList<Emprestimo>();
    
    private static List<Chave> chaves = new ArrayList<Chave>();
    
    private static int emprestimoId = 1;
    private boolean salvo = false;
    
    private String mensagem;
    
    @EJB
    private EmprestimoControle emprestimocontrole;
    
    
    public boolean isSalvo() {
        return salvo;
    }

    public void setSalvo(boolean salvo) {
        this.salvo = salvo;
    }

    public DevolucaoBean() {
        super();
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimocontrole.getEmprestimosAtivos();
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        DevolucaoBean.emprestimos = emprestimos;
    }
    

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public List<Chave> getChaves() {        
        return DevolucaoBean.chaves;
    }

    public void setChaves(List<Chave> chaves) {
        DevolucaoBean.chaves = chaves;
    }

    
    public String novo() {
        this.emprestimo = new Emprestimo();
        this.salvo = false;
        return "emprestimocad";
    }

    public String editar(Emprestimo oEmprestimo) {
        //this.emprestimo = emprestimocontrole.   
        //this.emprestimo = JPADAO.getInstancia().procurar(Emprestimo.class, oEmprestimo.getId());
        salvo = false;
        return "emprestimocad";
    }

    public String excluir(Emprestimo oEmprestimo) {
        emprestimos.remove(oEmprestimo);
        return "emprestimolist";
    }

    public String excluir() {        
        //JPADAO.getInstancia().excluir(emprestimo);
        
        return "devolucaolist";
    }

    
    public String devolucao(){                
        Query cons = null;//= JPADAO.getInstancia().getEM().createQuery("Select e from Emprestimo e where e.dataDevolucao is null");
        this.salvo = false;
        emprestimos = cons.getResultList();
        return "devolucaoconsulta";
    }
    
    public String devolver(Emprestimo oEmprestimo){
      oEmprestimo.setDataDevolucao(new Date(System.currentTimeMillis()));
      //emprestimo = JPADAO.getInstancia().procurar(Emprestimo.class, oEmprestimo.getId());
        try {
            //JPADAO.getInstancia().salvar(oEmprestimo);
            this.salvo = true;
        } catch (Exception e) {
            this.salvo = false;
        }
      
      return "devolucaoconsulta";
    }
    public String salvar() {
        System.out.println("Devolução salva: " + emprestimo.getId());
        //JPADAO.getInstancia().salvar(emprestimo);
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
        DevolucaoBean.emprestimoId = emprestimoId;
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
        DevolucaoBean.devolucoes = devolucoes;
    }

    
}
