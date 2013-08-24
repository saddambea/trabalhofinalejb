/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Chave;

@FacesConverter(forClass = Chave.class)
public class ChaveConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
// pegar o ID da chave, recebido pelo ‘value'
        int idChave = Integer.parseInt(value);
// buscar a chave no ChaveBean, via método estático
        Chave cat = ChaveBean.buscarChave(idChave);
        return cat;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
// o objeto chave é o parâmetro 'value'
        Chave cat = (Chave) value;
// chave é representada pelo seu ID
        return Integer.toString(cat.getId());
    }
}