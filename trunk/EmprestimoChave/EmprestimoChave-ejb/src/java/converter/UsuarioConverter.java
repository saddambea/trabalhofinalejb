/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import controle.UsuarioControle;
import dao.JPADAO;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Usuario;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {
    @EJB
    private JPADAO conexao;
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
// pegar o ID da chave, recebido pelo ‘value'
        int idUsuario = Integer.parseInt(value);
// buscar a chave no ChaveBean, via método estático
        return conexao.procurar(Usuario.class, idUsuario);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
// o objeto chave é o parâmetro 'value'
        Usuario usu  = (Usuario) value;
// chave é representada pelo seu ID
        return Integer.toString(usu.getId());
    }
}