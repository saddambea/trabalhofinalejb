/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import controle.ChaveControle;
import javax.faces.bean.ManagedBean;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import modelo.Chave;

/**
 *
 * @author dflenzi
 */
@ManagedBean
@SessionScoped
public class ChaveBean {

    /**
     * Creates a new instance of ChaveBean
     */
    @EJB
    ChaveControle chavecontrole;
    private Chave chave;
    private static List<Chave> chaves = new ArrayList<Chave>();
    private static int chaveId = 1;
    private boolean salvo = false;
    
    public boolean isSalvo() {
        return salvo;
    }

    public void setSalvo(boolean salvo) {
        this.salvo = salvo;
    }

    public ChaveBean() {
        super();
    }

    public List<Chave> getChaves() throws Exception{
        
        ChaveBean.chaves = chavecontrole.getChaves();
        return  ChaveBean.chaves;
    }

    public void setChaves(List<Chave> chaves) {
        ChaveBean.chaves = chaves;
    }

    public Chave getChave() {
        return chave;
    }

    public void setChave(Chave chave) {
        this.chave = chave;
    }

    public String novo() {
        this.chave = new Chave();
        this.salvo = false;
        return "chavecad";
    }

    public String editar(Chave oChave) {
        try {
            this.chave = chavecontrole.getChave(oChave.getId());
        } catch (Exception e) {
            this.chave = new Chave();
        }
        
        salvo = false;
        return "chavecad";
    }

    public String excluir(Chave oChave) throws Exception{        
        chavecontrole.excluir(chavecontrole.getChave(oChave.getId()));
        return "chavelist";
    }

    public String excluir() throws Exception{
        chavecontrole.excluir(chave);
        return "chavelist";
    }

    public String salvar() throws Exception{
        System.out.println("Chave salvo: " + this.chave.getSigla());
        chavecontrole.salvar(this.chave);
        salvo = true;
        return "chavelist";
    }

    public String cancelar() {
        this.salvo = false;
        return "principal";
    }

    public Chave buscarChave(int idChave) {
        try {
            return chavecontrole.getChave(idChave);
        } catch (Exception e) {
            return new Chave();
        }
        
    }

    
    public String getRestritoStr() {
        if(chave.getRestrito())
            return "Sim";
        else
          return "Não";
    }

    public static int getChaveId() {
        return chaveId;
    }

    public static void setChaveId(int chaveId) {
        ChaveBean.chaveId = chaveId;
    }

}
