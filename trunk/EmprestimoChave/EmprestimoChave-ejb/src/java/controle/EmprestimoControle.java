/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import modelo.ChaveDataModel;
import com.sun.media.sound.JARSoundbankReader;
import dao.JPADAO;
import javax.faces.bean.ManagedBean;
import modelo.Emprestimo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
public class EmprestimoControle {

    /**
     * Creates a new instance of EmprestimoControle
     */
    private Emprestimo emprestimo;
    private boolean autenticar;
    private static List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
    
    private static List<Chave> chaves = new ArrayList<Chave>();
    
    private static List<Chave> chavesselecionadas = new ArrayList<Chave>();
    
     private ChaveDataModel chavesModel = new ChaveDataModel(chaves); 
    
    private static int emprestimoId = 1;
    private boolean salvo = false;
    
    private String mensagem;
    
    private Usuario usuario = new Usuario();
    private Usuario usuarioBusca = new Usuario();
    
    public boolean isSalvo() {
        return salvo;
    }

    public void setSalvo(boolean salvo) {
        this.salvo = salvo;
    }

    public EmprestimoControle() {
        super();
    }

    public List<Emprestimo> getEmprestimos() {
        Query cons = JPADAO.getInstancia().getEM().createQuery("Select c from Emprestimo c");
        EmprestimoControle.emprestimos = cons.getResultList();
        return  EmprestimoControle.emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        EmprestimoControle.emprestimos = emprestimos;
    }
    

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public List<Chave> getChaves() {        
        return EmprestimoControle.chaves;
    }

    public void setChaves(List<Chave> chaves) {
        EmprestimoControle.chaves = chaves;
    }
    
    public List<Chave> getChavesselecionadas() {
        return chavesselecionadas;
    }

    public void setChavesselecionadas(List<Chave> chavesselecionadas) {
        EmprestimoControle.chavesselecionadas = chavesselecionadas;
    }
    

    
    public String novo() {
        this.emprestimo = new Emprestimo();
        this.salvo = false;
        return "emprestimocad";
    }

    public String editar(Emprestimo oEmprestimo) {
        this.emprestimo = JPADAO.getInstancia().procurar(Emprestimo.class, oEmprestimo.getId());
        salvo = false;
        return "emprestimocad";
    }

    public String excluir(Emprestimo oEmprestimo) {
        emprestimos.remove(oEmprestimo);
        return "emprestimolist";
    }

    public String excluir() {        
        JPADAO.getInstancia().excluir(emprestimo);
        
        return "emprestimolist";
    }

    
    public String buscarChaves(Usuario oUsuario){
      this.salvo=false;  
      
      Query cons;        
      
      cons = JPADAO.getInstancia().getEM().createQuery("Select a.chave From Autorizacao a where " +
                                                       " a.usuario.codigo = :pcodigo and (a.dataFim is null or a.dataFim > :pdata) "
                                                     + " and not exists (Select e From Emprestimo e Where e.chave = a.chave and e.dataDevolucao is null) ");      
      cons.setParameter("pcodigo", oUsuario.getCodigo());
      cons.setParameter("pdata", new Date(System.currentTimeMillis()));
      
      this.chaves = cons.getResultList();
      this.chavesModel = new ChaveDataModel(chaves);
      this.autenticar = !this.chaves.isEmpty();
      this.usuarioBusca = FuncoesGeraisControle.getUsuarioByCodigo(usuario.getCodigo());
      return "emprestimoconsulta";
    }
    
    public String emprestar(Usuario oBalconista, Integer senha) {
        Emprestimo emp;
        this.salvo=false;
        
        Usuario usuarioBusca = FuncoesGeraisControle.getUsuarioByCodigo(this.usuario.getCodigo());        
        
        if (!AutenticacaoControle.getUsuarioAutenticacao(this.usuario.getCodigo(), senha)){
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Usuário/Senha inválido","Não foi possível fazer login com o usuário/senha informados");
                             FacesContext.getCurrentInstance().addMessage("login", msg);
            
            return "emprestimoconsulta";
        }
        else
        {
            
            if (chavesselecionadas.size() > 0){                                
                JPADAO.getInstancia().getEM().getTransaction().begin();
                this.mensagem="";
                for(Chave cha : chavesselecionadas ){
                        //
                    emp = new  Emprestimo();
                    emp.setBalconista(JPADAO.getInstancia().procurar(Usuario.class, oBalconista.getId()));
                    emp.setUsuario(this.usuarioBusca);
                    emp.setChave(cha);
                    emp.setDataEmprestimo(new Date(System.currentTimeMillis()));   
                    JPADAO.getInstancia().getEM().merge(emp);
                    if(!this.mensagem.isEmpty())
                    this.mensagem = "\n" +this.mensagem + "," + cha.getSigla() ;
                    else          
                      this.mensagem = this.mensagem + cha.getSigla();
                    this.salvo=true;

                }
                JPADAO.getInstancia().getEM().getTransaction().commit();
            }
            this.autenticar = false;
            return "emprestimoconsulta";
        }
            
    }
    public String salvar() {
        System.out.println("Emprestimo salvo: " + emprestimo.getId());
        JPADAO.getInstancia().salvar(emprestimo);
        this.salvo=true;
        
        return "emprestimocad";
    }

    public String cancelar() {
        this.salvo = false;
        return "emprestimoconsulta";
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
        this.autenticar=false;  
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
        EmprestimoControle.emprestimoId = emprestimoId;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public ChaveDataModel getChavesModel() {
        return chavesModel;
    }

    public boolean isAutenticar() {
        return autenticar;
    }

    public void setAutenticar(boolean autenticar) {
        this.autenticar = autenticar;
    }

    public Usuario getUsuarioBusca() {
        return usuarioBusca;
    }

    public void setUsuarioBusca(Usuario usuarioBusca) {
        this.usuarioBusca = usuarioBusca;
    }
    
    
    
    
    
}
