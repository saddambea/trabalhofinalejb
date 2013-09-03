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
import modelo.Chave;
import modelo.Usuario;


@Stateless
public class AutorizacaoDAO extends DAOBase<Autorizacao> {

    public AutorizacaoDAO() {
        super(Autorizacao.class);
    }
    
    public List<Chave> listarChavesUsuario(Usuario usuario, Date data) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pcodigo", usuario.getCodigo());
        params.put("pdata", data);
        
        return dao.listarNamedQuery("autorizacao.chavesusuario", params);
        
    }
    
    
}
