/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.UsuarioControle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author daniel
 */
@ManagedBean
@SessionScoped

public class UsuarioVisao {
  @EJB(beanName = "UsuarioControle")  
  private UsuarioControle usuariocontrole;
    
}
