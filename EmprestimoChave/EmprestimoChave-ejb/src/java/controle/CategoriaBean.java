/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import javax.faces.bean.ManagedBean;
import modelo.Categoria;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author dflenzi
 */
@ManagedBean
@SessionScoped
public class CategoriaBean {

    /**
     * Creates a new instance of CategoriaBean
     */
    private Categoria categoria;
    private static List<Categoria> categorias = new ArrayList<Categoria>();
    private static int categoriaId = 1;
    private boolean salvo = false;

    public boolean isSalvo() {
        return salvo;
    }

    public void setSalvo(boolean salvo) {
        this.salvo = salvo;
    }

    public CategoriaBean() {
        super();
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        CategoriaBean.categorias = categorias;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String novo() {
        this.categoria = new Categoria();
        this.salvo = false;
        return "categoriacad";
    }

    public String editar(Categoria oCategoria) {
        this.categoria = oCategoria;
        salvo = false;
        return "categoriacad";
    }

    public String excluir(Categoria oCategoria) {
        categorias.remove(oCategoria);
        return "categorialist";
    }

    public String excluir() {
        categorias.remove(categoria);
        return "categorialist";
    }

    public String salvar() {

        System.out.println("Categoria salvo: " + categoria.getDescricao());
        if (categoria.getId() == 0) {
            categoria.setId(categoriaId);
            categoriaId++;
            categorias.add(categoria);
            this.salvo = true;
        }
        return "categoriacad";
    }

    public String cancelar() {
        this.salvo = false;
        return "principal";
    }

    public static Categoria buscarCategoria(int idCategoria) {
        for (Categoria cat : categorias) {
            if (cat.getId() == idCategoria) {
                return cat;
            }
        }
        return null;
    }
}
