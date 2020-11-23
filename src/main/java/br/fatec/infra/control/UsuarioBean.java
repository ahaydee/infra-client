package br.fatec.infra.control;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import br.fatec.infra.model.TipoPerfil;
import br.fatec.infra.model.Usuario;
import br.fatec.infra.rest.client.UsuarioRESTClient;
import br.fatec.infra.util.SessionContext;
import br.fatec.infra.dto.CredenciaisDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@ManagedBean
@RequestScoped
public class UsuarioBean implements Serializable{
    private static final long serialVersionUID = 1L;
	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios;

	public UsuarioBean() {
		UsuarioRESTClient rest = new UsuarioRESTClient();
		usuarios = rest.findAll(); 
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public String gravar() {
		UsuarioRESTClient rest = new UsuarioRESTClient();
		if (usuario.getId() == null) {
			rest.create(usuario);
			usuario = new Usuario();
			usuario.setPedidos(new ArrayList());	
			return "/protected/usuarios?faces-redirect=true";		
		}
		else {
			usuario.setPedidos(new ArrayList());	
			usuario = rest.edit(usuario);
		}
		return null;	
	}
	
	public String cancelar() {
		this.usuario = new Usuario();
		return null;
	}
	
	public String excluir(Usuario c) {
		UsuarioRESTClient rest = new UsuarioRESTClient();
		if (!rest.delete(c.getId())) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage("Não foi possível excluir o usuário " + c.getNome());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}
		else {
			usuarios.remove(c);
		}
		return null;
	}
	

	public void onEdit(RowEditEvent event) {
		Usuario p = (Usuario) event.getObject();
		this.usuario = p;
		this.gravar();	
		FacesMessage msg = new FacesMessage("Usuário atualizado", p.getNome().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void onCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edição cancelada");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
