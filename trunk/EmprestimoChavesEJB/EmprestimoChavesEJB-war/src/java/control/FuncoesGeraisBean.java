/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import controle.FuncoesGeraisControle;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Query;
import modelo.Usuario;



/**
 *
 * @author daniel
 */
@ManagedBean
@SessionScoped
public class FuncoesGeraisBean {
    @EJB
    FuncoesGeraisControle funcoesgeraiscontrole;
    /**
     * Creates a new instance of FuncoesGeraisBean
     */
    public FuncoesGeraisBean() {
    }
    
    public String getDataHora(Date data){
     SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");  
     if(data !=null)
     {    
       return f.format(data);
     }          
     else
       return "";
    }
    public String getData(Date data){
     SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");  
     if(data !=null)
     {    
       return f.format(data);
     }          
     else
       return "";
    }
    
    
    public String getBooleanToString(boolean bol){
        if(bol){
            return "Sim";
        }
        else
            return "Não";
    }
    
    public Usuario getUsuarioByCodigo(Integer codigo){
        try {
            return funcoesgeraiscontrole.getUsuarioByCodigo(codigo);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static Date getDataHoraAtual(){
        return new Date(System.currentTimeMillis());
    }
}
