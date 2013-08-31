/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.ejb.Stateless;
import modelo.TipoUsuario;

@Stateless
public class TipoUsuarioDAO extends DAOBase<TipoUsuario>{
    
    public TipoUsuarioDAO() {
        super(TipoUsuario.class);
    }
    
    
}
