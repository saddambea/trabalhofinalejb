/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Categoria;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
// pegar o ID da categoria, recebido pelo ‘value'
        int idCategoria = Integer.parseInt(value);
// buscar a categoria no CategoriaBean, via método estático
        Categoria cat = CategoriaBean.buscarCategoria(idCategoria);
        return cat;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
// o objeto categoria é o parâmetro 'value'
        Categoria cat = (Categoria) value;
// categoria é representada pelo seu ID
        return Integer.toString(cat.getId());
    }
}