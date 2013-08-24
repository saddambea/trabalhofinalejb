/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.JPADAO;
import javax.faces.bean.ManagedBean;
import modelo.Chave;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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

    public List<Chave> getChaves() {
        Query cons = JPADAO.getInstancia().getEM().createQuery("Select c from Chave c");
        ChaveBean.chaves = cons.getResultList();
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
        this.chave = oChave;
        salvo = false;
        return "chavecad";
    }

    public String excluir(Chave oChave) {
        JPADAO.getInstancia().excluir(oChave);
        return "chavelist";
    }

    public String excluir() {
        JPADAO.getInstancia().excluir(chave);
        return "chavelist";
    }

    public String salvar() {
        System.out.println("Chave salvo: " + chave.getSigla());
        JPADAO.getInstancia().salvar(chave);
        salvo = true;
        return "chavelist";
    }

    public String cancelar() {
        this.salvo = false;
        return "principal";
    }

    public static Chave buscarChave(int idChave) {
        for (Chave cat : chaves) {
            if (cat.getId() == idChave) {
                return cat;
            }
        }
        return null;
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
