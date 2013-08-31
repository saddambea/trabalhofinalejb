/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import controle.TipoUsuarioControle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.TipoUsuario;

@FacesConverter(value = "tipoUsuarioConverter")
@ManagedBean
@RequestScoped

public class TipoUsuarioConverter implements Converter {
    @EJB
    TipoUsuarioControle tipousuariocontrole;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
// pegar o ID do tipo, recebido pelo ‘value'
        int idTipo = Integer.parseInt(value);
// buscar a categoria no CategoriaBean, via método estático
        TipoUsuario tip = null;
        try {
            tip = tipousuariocontrole.buscarTipoUsuario(idTipo);
        } catch (Exception ex) {
            Logger.getLogger(TipoUsuarioConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tip;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
// o objeto categoria é o parâmetro 'value'
        TipoUsuario tip = (TipoUsuario) value;
// categoria é representada pelo seu ID
        return Integer.toString(tip.getId());
    }
    
    
}