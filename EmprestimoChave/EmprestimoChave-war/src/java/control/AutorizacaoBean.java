/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.JPADAO;
import javax.faces.bean.ManagedBean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Query;
import modelo.Usuario;
import modelo.Chave;
import modelo.Autorizacao;

/**
 *
 * @author dflenzi
 */
@ManagedBean
@SessionScoped
public class AutorizacaoBean {

    /**
     * Creates a new instance of UsuarioBean
     */
    private Usuario usuario;
    private Autorizacao autorizacao;
    private Chave chave;
    
    private static List<Autorizacao> autorizacoes = new ArrayList<Autorizacao>();
    private static int autorizacaoId = 1;
    private boolean salvo = false;
    private String mensagem;
    

    public boolean isSalvo() {
        return salvo;
    }

    public void setSalvo(boolean salvo) {
        this.salvo = salvo;
    }

    public AutorizacaoBean() {
    }

    public List<Autorizacao> getAutorizacoes() {
        Query cons =null;//  JPADAO.getInstancia().getEM().createQuery("Select a from Autorizacao a");
        autorizacoes = cons.getResultList();
        return autorizacoes;
    }

    public void setAutorizacoes(List<Autorizacao> autorizacoes) {
        AutorizacaoBean.autorizacoes = autorizacoes;
    }

    public Autorizacao getAutorizacao() {        
        return autorizacao;
    }

    public void setAutorizacao(Autorizacao autorizacao) {
        this.autorizacao = autorizacao;
    }

    public String novo() {
        this.autorizacao = new Autorizacao();
        this.salvo = false;
        return "autorizacaocad";
    }
    
    public String novo(Usuario oUsuario) {
        this.autorizacao = new Autorizacao();
        this.autorizacao.setUsuario(oUsuario);
        this.salvo = false;
        return "autorizacaocad";
    }
    

    public String editar(Autorizacao oAutorizacao) {
        this.autorizacao = null;//JPADAO.getInstancia().procurar(Autorizacao.class, oAutorizacao.getId());                
        salvo = false;
        return "autorizacaocad";
    }
    

    public String excluir(Autorizacao oAutorizacao) {
        //JPADAO.getInstancia().excluir(oAutorizacao);
              
        return "autorizacaolist";
    }

    public String excluir() {
        autorizacoes.remove(autorizacao);
        return "autorizacaolist";
    }

    public String salvar() {
        System.out.println("Autorização salva: " + autorizacao.toString());
        try {            
            Query cons =null; //JPADAO.getInstancia().getEM().createQuery("Select a from Autorizacao a where a.usuario = :pusuario and  a.chave = :pchave and (a.dataFim is null or a.dataFim <= :pdata)" ); 
            cons.setParameter("pusuario", this.autorizacao.getUsuario());
            cons.setParameter("pchave", this.autorizacao.getChave());
            cons.setParameter("pdata", FuncoesGeraisBean.getDataHoraAtual());
            
            if(cons.getResultList().isEmpty())
              //JPADAO.getInstancia().salvar(autorizacao);
                this.salvo = true;
            else{
              FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Chave já autorizada","A chave já está autorizada para o usuário informado");
                                 FacesContext.getCurrentInstance().addMessage("login", msg);
            }                     
                  
            this.salvo = true;
            
        } catch (Exception e) {
            this.salvo = false;            
        }
        return "manutencaousuariolist";
    }

    public String cancelar() {
        this.salvo = false;
        return "manutencaousuariolist";
    }
    

    public static Autorizacao buscarAutorizacao(int idAutorizacao) {
        for (Autorizacao aut : autorizacoes) {
            if (aut.getId() == idAutorizacao) {
                return aut;
            }
        }
        return null;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Chave getChave() {
        return chave;
    }

    public void setChave(Chave chave) {
        this.chave = chave;
    }
    
}
