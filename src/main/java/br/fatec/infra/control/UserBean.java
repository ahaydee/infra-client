package br.fatec.infra.control;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.fatec.infra.rest.client.UsuarioRESTClient;
import br.fatec.infra.util.SessionContext;
import br.fatec.infra.dto.CredenciaisDTO;



@ManagedBean
@RequestScoped
public class UserBean {
	private CredenciaisDTO usuario = new CredenciaisDTO();
	
	public UserBean() {
	}

	public CredenciaisDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(CredenciaisDTO usuario) {
		this.usuario = usuario;
	}
	
	public String autenticar() {
		UsuarioRESTClient client = new UsuarioRESTClient();
		if (client.authenticate(usuario)) {
			return "/protected/catalogo?faces-redirect=true";
		}
		FacesMessage msg = new FacesMessage("Login/senha inválidos");
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return null;
	}
			
	public String logout() {
		SessionContext.getInstance().encerrarSessao();
		return "/index?faces-redirect=true";
	}
}
