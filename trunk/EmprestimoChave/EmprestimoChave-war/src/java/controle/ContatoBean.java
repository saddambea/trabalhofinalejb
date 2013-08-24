/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import javax.faces.bean.ManagedBean;
import modelo.Contato;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import modelo.Categoria;


/**
 *
 * @author dflenzi
 */
@ManagedBean
@SessionScoped
public class ContatoBean {

    /**
     * Creates a new instance of ContatoBean
     */
    private Contato contato;
    private static List<Contato> contatos = new ArrayList<Contato>();
    private static int contatoId = 1;
    private boolean salvo = false;
    private Categoria categoria;

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public boolean isSalvo() {
        return salvo;
    }

    public void setSalvo(boolean salvo) {
        this.salvo = salvo;
    }

    public ContatoBean() {
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        ContatoBean.contatos = contatos;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public String novo() {
        this.contato = new Contato();
        this.salvo = false;
        return "contatocad";
    }

    public String editar(Contato oContato) {
        this.contato = oContato;
        salvo = false;
        return "contatocad";
    }

    public String excluir(Contato oContato) {
        contatos.remove(oContato);
        return "contatolist";
    }

    public String excluir() {
        contatos.remove(contato);
        return "contatolist";
    }

    public String salvar() {

        System.out.println("Contato salvo: " + contato.getNome());
        if (contato.getId() == 0) {
            contato.setId(contatoId);
            contatoId++;
            contatos.add(contato);
            this.salvo = true;
        }
        return "contatocad";
    }

    public String cancelar() {
        this.salvo = false;
        return "principal";
    }

    public void validarEmail(FacesContext context,
            UIComponent component,
            Object valor) throws ValidatorException {
// implementação da lógica de validação
        String email = (String) valor;
        if (!email.contains("@")) {
// lançar exceção, com a mensagem a ser exibida.
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "E-mail Inválido.", /*summary*/
                    "Este e-mail é inválido.")); /*detail*/
        }
    }
}
