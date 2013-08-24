/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.JPADAO;
import javax.faces.bean.ManagedBean;
import modelo.TipoUsuario;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author dflenzi
 */
@ManagedBean
@SessionScoped
public class TipoUsuarioControle {

    /**
     * Creates a new instance of CategoriaBean
     */
    private TipoUsuario tipousuario;
    private static List<TipoUsuario> tipousuarios = new ArrayList<TipoUsuario>();
    private static int tipoUsuarioId = 1;
    private boolean salvo = false;

    public boolean isSalvo() {
        return salvo;
    }

    public void setSalvo(boolean salvo) {
        this.salvo = salvo;
    }

    public TipoUsuarioControle() {
        super();
    }

    public List<TipoUsuario> getTipoUsuarios() {
        if(tipousuarios.isEmpty()){
            Query cons =  JPADAO.getInstancia().getEM().createQuery("SELECT t From TipoUsuario t");
            tipousuarios = cons.getResultList();
        }
        return tipousuarios;
    }

    public void setTipoUsuarios(List<TipoUsuario> tipousuarios) {
        TipoUsuarioControle.tipousuarios = tipousuarios;
    }

    public TipoUsuario getTipoUsuario() {
        return tipousuario;
    }

    public void setTipoUsuario(TipoUsuario tipousuario) {
        this.tipousuario = tipousuario;
    }

    public String novo() {
        this.tipousuario = new TipoUsuario();
        this.salvo = false;
        return "tipousuariocad";
    }

    public String editar(TipoUsuario oTipoUsuario) {
        this.tipousuario = oTipoUsuario;
        salvo = false;
        return "tipousuariocad";
    }

    public String excluir(TipoUsuario oTipoUsuario) {
        JPADAO.getInstancia().excluir(oTipoUsuario);
        return "tipousuariolist";
    }

    public String excluir() {
        JPADAO.getInstancia().excluir(tipousuario);
        return "tipousuariolist";
    }
    

    public String salvar() {
        JPADAO.getInstancia().salvar(tipousuario);
        this.salvo = true;
        return "tipousuariocad";
    }

    public String cancelar() {
        this.salvo = false;
        return "principal";
    }

    public static TipoUsuario buscarTipoUsuario(int idTipoUsuario) {
        for (TipoUsuario tip : tipousuarios) {
            if (tip.getId() == idTipoUsuario) {
                return tip;
            }
        }
        return null;
    }

  
    
    
    
    
    
}
