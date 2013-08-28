/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.AutenticacaoControle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author daniel
 */
@ManagedBean
@SessionScoped
public class AutenticacaoVisao {
    
   @EJB
   private AutenticacaoControle autenticacaocontrol;
    
   public boolean login(Integer codigo, Integer senha) {
       return autenticacaocontrol.login(codigo, senha);
   }
}
