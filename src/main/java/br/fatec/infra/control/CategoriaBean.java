package br.fatec.infra.control;


import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import br.fatec.infra.model.Categoria;
import java.util.List;

@ManagedBean
@RequestScoped
public class CategoriaBean implements Serializable{
    private static final long serialVersionUID = 1L;
	private Categoria categoria;
	private List<Categoria> categorias;
	
	public CategoriaBean() {
		
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
}
