/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;


import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Usuario;

/**
 *
 * @author daniel
 */
@Stateless
public class FuncoesGeraisControle{
    @EJB
    private dao.JPADAOXX conexao;

    /**
     * Creates a new instance of FuncoesGeraisControle
     */
    public FuncoesGeraisControle() {
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
            return conexao.buscarSimples(Usuario.class, "codigo", codigo);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static Date getDataHoraAtual(){
        return new Date(System.currentTimeMillis());
    }
}
