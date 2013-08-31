/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import controle.TipoUsuarioControle;
import javax.faces.bean.ManagedBean;
import modelo.TipoUsuario;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.persistence.Query;

/**
 *
 * @author dflenzi
 */
@ManagedBean
@SessionScoped
public class TipoUsuarioBean {

    /**
     * Creates a new instance of CategoriaBean
     */
    private TipoUsuario tipousuario;
    private static List<TipoUsuario> tipousuarios = new ArrayList<TipoUsuario>();
    private static int tipoUsuarioId = 1;
    private boolean salvo = false;
    @EJB
    private TipoUsuarioControle tipousuariocontrole;

    public boolean isSalvo() {
        return salvo;
    }

    public void setSalvo(boolean salvo) {
        this.salvo = salvo;
    }

    public TipoUsuarioBean() {
        super();
    }

    public List<TipoUsuario> getTipoUsuarios() {
        return tipousuariocontrole.getTipoUsuarios();
    }

    public void setTipoUsuarios(List<TipoUsuario> tipousuarios) {
        TipoUsuarioBean.tipousuarios = tipousuarios;
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
        tipousuariocontrole.excluir(oTipoUsuario);
        return "tipousuariolist";
    }


    public String salvar() {
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
