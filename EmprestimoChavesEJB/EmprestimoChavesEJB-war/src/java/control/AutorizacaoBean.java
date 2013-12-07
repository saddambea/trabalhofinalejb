/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import controle.AutorizacaoControle;
import javax.faces.bean.ManagedBean;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
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
    @EJB
    private AutorizacaoControle autorizacaocontrole;
    private Usuario usuario;
    private Autorizacao autorizacao = new Autorizacao();
    private Chave chave;
    private List<Autorizacao> autorizacoes = new ArrayList<Autorizacao>();
    private boolean salvo = false;
    

    public boolean isSalvo() {
        return salvo;
    }

    public void setSalvo(boolean salvo) {
        this.salvo = salvo;
    }

    public AutorizacaoBean() {
    }

    public List<Autorizacao> getAutorizacoes() {
        return autorizacaocontrole.getAutorizacoes();
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
        this.autorizacao = oAutorizacao;
        salvo = false;
        return "autorizacaocad";
    }
    

    public String excluir(Autorizacao oAutorizacao) {
        autorizacaocontrole.excluir(oAutorizacao);
        return "autorizacaolist";
    }

    public String salvar() {
        System.out.println("Autorização salva: " + autorizacao.toString());
        try {        
            
            
            if(autorizacaocontrole.getAssinaRSA() && autorizacaocontrole.getAssinaDSA()){
              autorizacaocontrole.salvar(autorizacao);
              this.salvo = true;  
            } else {
                System.exit(0);
                this.salvo = false;
            }
            
            
        } catch (Exception e) {
            this.salvo = false;            
        }
        return "manutencaousuariolist";
    }

    public String cancelar() {
        this.salvo = false;
        return "manutencaousuariolist";
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
