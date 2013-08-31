/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import modelo.Autorizacao;
import modelo.Usuario;


@Stateless
public class UsuarioDAO extends DAOBase<Usuario>{
    
    public UsuarioDAO() {
        super(Usuario.class);
    }

    @Override
    public List<Usuario> listarTodos() throws Exception {
        return super.listarTodos();
    }
    
    public Usuario buscarSimples(int cod) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("codigo", cod);
        
        return dao.listarNamedQueryUnico("usuario.listarSimples", params);
        
    }
}
