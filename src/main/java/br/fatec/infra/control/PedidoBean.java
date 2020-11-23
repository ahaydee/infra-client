package br.fatec.infra.control;


import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import br.fatec.infra.model.Pedido;
import br.fatec.infra.dto.CredenciaisDTO;
import br.fatec.infra.rest.client.CategoriaRESTClient;
import br.fatec.infra.rest.client.PedidoRESTClient;
import br.fatec.infra.rest.client.ServicoRESTClient;
import br.fatec.infra.util.SessionContext;

import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class PedidoBean implements Serializable{
    private static final long serialVersionUID = 1L;
    private Pedido pedido;
    private String login;
    private List<Pedido> pedidos;
	private Long id;
    
    public PedidoBean() {
    	CredenciaisDTO u = (CredenciaisDTO) SessionContext.getInstance().getAttribute("usuario");
    	PedidoRESTClient rest = new PedidoRESTClient();
    	if (u.getAdmin() == true) {
    		pedidos = rest.findAll();	
    	}
    	else {
    		login = u.getLogin();
    		pedidos = rest.findByUsuario(login);	
    	}
	}
    public void inicializar() {
	    if (id != null) {
	    	PedidoRESTClient rest = new PedidoRESTClient();
	    	pedido = rest.find(id);
	    }
    }

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public String pagPedido() {
		this.pedido = new Pedido();
		return "/protected/pedido?faces-redirect=true";
	}
	
	public String pagPedido(Pedido pedido) {
		this.pedido = pedido;
		return "/protected/pedido?faces-redirect=true";
	}
	
	public String gravar() {
    	PedidoRESTClient rest = new PedidoRESTClient();
		if (pedido.getId() == null) {
			rest.create(pedido);
			pedido = new Pedido();	
			return "/protected/pedido?faces-redirect=true";			
		}
		else {
			pedido = rest.edit(pedido);
		}
		return null;		
	}
	
	public String aprovar() {
    	PedidoRESTClient rest = new PedidoRESTClient();
		if (pedido.getId() != null) {
			pedido.setSituacaos(new ArrayList<String>());
			pedido.getUsuario().setPerfil();
			pedido = rest.aprovar(pedido);
			return "/protected/pedido?faces-redirect=true&id="+id;		
		}
		return null;		
	}
	
	public String reprovar() {
    	PedidoRESTClient rest = new PedidoRESTClient();
		if (pedido.getId() != null) {
			pedido.setSituacaos(new ArrayList<String>());
			pedido.getUsuario().setPerfil();
			pedido = rest.reprovar(pedido);
			return "/protected/pedido?faces-redirect=true&id="+id;			
		}
		return null;		
	}
	
	public String cancelar() {
		this.pedido = new Pedido();
		return null;
	}
	
	public String excluir(Pedido c) {
    	PedidoRESTClient rest = new PedidoRESTClient();
		if (!rest.delete(c.getId())) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage("Não foi possível excluir a requisição");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}
		else {
			pedidos.remove(c);
		}
		return null;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
