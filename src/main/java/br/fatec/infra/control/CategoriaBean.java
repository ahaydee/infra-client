package br.fatec.infra.control;


import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import br.fatec.infra.model.Categoria;
import br.fatec.infra.rest.client.CategoriaRESTClient;

import java.util.List;

@ManagedBean
@RequestScoped
public class CategoriaBean implements Serializable{
    private static final long serialVersionUID = 1L;
	private Categoria categoria = new Categoria();
	private List<Categoria> categorias;
	
	public CategoriaBean() {
		CategoriaRESTClient rest = new CategoriaRESTClient();
		categorias = rest.findAll();
		categoria = new Categoria();
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
	public String pagPrincipal() {
		CategoriaRESTClient rest = new CategoriaRESTClient();
		categorias = rest.findAll();
		return "/protected/catalogo?faces-redirect=true";		
	}
	
	public String pagCategoria() {
		this.categoria = new Categoria();
		return "/protected/categoria?faces-redirect=true";
	}
	
	public String pagCategoria(Categoria categoria) {
		this.categoria = categoria;
		return "/protected/categoria?faces-redirect=true";
	}
	
	
	public String gravar() {
		CategoriaRESTClient rest = new CategoriaRESTClient();
		if (categoria.getId() == null) {
			rest.create(categoria);
			categoria = new Categoria();	
			return "/protected/catalogo?faces-redirect=true";			
		}
		else {
			categoria = rest.edit(categoria);
		}
		return null;		
	}
	
	public String cancelar() {
		this.categoria = new Categoria();
		return null;
	}
	
	public String excluir(Categoria c) {
		CategoriaRESTClient rest = new CategoriaRESTClient();
		if (!rest.delete(c.getId())) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage("Não foi possível excluir a categoria " + c.getNome());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}
		else {
			categorias.remove(c);
		}
		return null;
	}
	

	public void onEdit(RowEditEvent event) {
		Categoria p = (Categoria) event.getObject();
		this.categoria = p;
		this.gravar();	
		FacesMessage msg = new FacesMessage("Categoria atualizada", p.getNome().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edição cancelada");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
}
