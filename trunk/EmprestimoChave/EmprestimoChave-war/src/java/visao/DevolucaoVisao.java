/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.DevolucaoControle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author daniel
 */
@ManagedBean
@SessionScoped

public class DevolucaoVisao {
  @EJB(beanName = "DevolucaoControle")
  private DevolucaoControle devolucaocontrole;  
}
