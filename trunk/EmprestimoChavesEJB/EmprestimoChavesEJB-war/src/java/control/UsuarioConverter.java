/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import controle.UsuarioControle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Usuario;

@FacesConverter(value = "usuarioConverter")
@ManagedBean
@RequestScoped

public class UsuarioConverter implements Converter {
    @EJB
    private UsuarioControle usuariocontrole;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
// pegar o ID da chave, recebido pelo ‘value'
        int idUsuario = Integer.parseInt(value);
// buscar a chave no ChaveBean, via método estático
        Usuario usu = null;
        try {
            usu = usuariocontrole.buscarUsuario(idUsuario);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usu;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
// o objeto chave é o parâmetro 'value'
        Usuario usu  = (Usuario) value;
// chave é representada pelo seu ID
        return Integer.toString(usu.getId());
    }
}