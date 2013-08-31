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

/**
 *
 * @author abaehr
 */
@Stateless
public class AutorizacaoDAO extends DAOBase<Autorizacao> {

    public AutorizacaoDAO() {
        super(Autorizacao.class);
    }
    
    public List<Autorizacao> listarXPTO(int cod, Date data) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("codigo", cod);
        params.put("data", data);
        
        return dao.listarNamedQuery("autorizacao.listar", params);
        
    }
    
    
}
