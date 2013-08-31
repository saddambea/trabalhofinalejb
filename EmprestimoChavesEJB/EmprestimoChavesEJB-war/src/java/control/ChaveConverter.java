/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import controle.ChaveControle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.Chave;

@FacesConverter(value = "chaveConverter")
@ManagedBean
@RequestScoped

public class ChaveConverter implements Converter {
    @EJB
    ChaveControle chavecontrole;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("getAsObject");
        // pegar o ID da chave, recebido pelo ‘value'
        int idChave = Integer.parseInt(value);
      // buscar a chave no ChaveBean, via método estático
        Chave c = chavecontrole.getChave(idChave);
        return c;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
// o objeto chave é o parâmetro 'value'
        System.out.println("getAsString");
        Chave cat = (Chave) value;
// chave é representada pelo seu ID
        return Integer.toString(cat.getId());
    }
}