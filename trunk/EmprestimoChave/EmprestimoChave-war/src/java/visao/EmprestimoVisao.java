/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.EmprestimoControle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author daniel
 */
@ManagedBean
@SessionScoped

public class EmprestimoVisao {
  @EJB(beanName = "EmprestimoControle")
  private EmprestimoControle emprestimocontrole;  
}
