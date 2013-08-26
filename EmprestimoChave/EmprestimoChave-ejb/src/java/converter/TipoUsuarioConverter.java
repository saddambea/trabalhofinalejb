/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import controle.TipoUsuarioControle;
import dao.JPADAO;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.TipoUsuario;

@FacesConverter(forClass = TipoUsuario.class)
public class TipoUsuarioConverter implements Converter {
    @EJB            
    JPADAO conexao;
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
// pegar o ID do tipo, recebido pelo ‘value'
        int idTipo = Integer.parseInt(value);
// buscar a categoria no CategoriaBean, via método estático
        
        return conexao.procurar(TipoUsuario.class, idTipo);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
// o objeto categoria é o parâmetro 'value'
        TipoUsuario tip = (TipoUsuario) value;
// categoria é representada pelo seu ID
        return Integer.toString(tip.getId());
    }
}