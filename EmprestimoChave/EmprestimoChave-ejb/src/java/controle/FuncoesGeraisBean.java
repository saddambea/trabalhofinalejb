/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.JPADAO;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    
    public static Usuario getUsuarioByCodigo(Integer codigo){
        Query cons = JPADAO.getInstancia().getEM().createQuery("Select u From Usuario u where u.codigo = :pcodigo");
        cons.setParameter("pcodigo", codigo);
        try {
            return (Usuario) cons.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public static Date getDataHoraAtual(){
        return new Date(System.currentTimeMillis());
    }
}
