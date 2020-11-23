package br.fatec.infra.control;


import java.io.Serializable;
import java.text.DateFormat;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.fatec.infra.model.Servico;
import br.fatec.infra.model.Usuario;
import br.fatec.infra.dto.CredenciaisDTO;
import br.fatec.infra.model.Categoria;
import br.fatec.infra.model.Pedido;
import br.fatec.infra.rest.client.ServicoRESTClient;
import br.fatec.infra.rest.client.UsuarioRESTClient;
import br.fatec.infra.util.SessionContext;
import br.fatec.infra.rest.client.CategoriaRESTClient;
import br.fatec.infra.rest.client.PedidoRESTClient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean
@ViewScoped
public class ServicoBean implements Serializable{
    private static final long serialVersionUID = 1L;
    private Servico servico = new Servico();
    private List<Servico> servicos;
	private Long idCategoria;
	private Long idServico;
    private Categoria categoria;
    private Pedido pedido = new Pedido();
    
    public ServicoBean() {
    	//ServicoRESTClient rest = new ServicoRESTClient();
		//servicos = rest.findAll();		
    }
    
    public void inicializar() {
	    if (idCategoria != null) {
	    	CategoriaRESTClient catRest = new CategoriaRESTClient();
	    	categoria = catRest.find(idCategoria);
	    	ServicoRESTClient rest = new ServicoRESTClient();
			servicos = rest.findByCategoria(idCategoria);	
	    }
	    else {
		    if (idServico != null) {
		    	ServicoRESTClient rest = new ServicoRESTClient();
				servico = rest.find(idServico);	
		    }
	    }
    }

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Long getIdServico() {
		return idServico;
	}

	public void setIdServico(Long idServico) {
		this.idServico = idServico;
	}
	
	public String gravar() {
    	ServicoRESTClient rest = new ServicoRESTClient();
		if (servico.getId() == null) {
			servico.setCategoria(categoria);
			rest.create(servico);
			servico = new Servico();
			return "/protected/categoria?faces-redirect=true&id="+idCategoria;			
		}
		else {
			servico.setCategoria(categoria);
			servico = rest.edit(servico);
		}
		return null;		
	}
	
	public String cancelar() {
		this.servico = new Servico();
		return null;
	}
	
	public String excluir(Servico c) {
    	ServicoRESTClient rest = new ServicoRESTClient();
		if (!rest.delete(c.getId())) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage("Não foi possível excluir o serviço " + c.getDescricao());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}
		else {
			servicos.remove(c);
		}
		return null;
	}
	

	public void onEdit(RowEditEvent event) {
		Servico p = (Servico) event.getObject();
		this.servico = p;
		this.gravar();	
		FacesMessage msg = new FacesMessage("Serviço atualizado", p.getDescricao().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edição cancelada");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String solicitar() {
		//pega e seta o usuário
    	CredenciaisDTO u = (CredenciaisDTO) SessionContext.getInstance().getAttribute("usuario");
    	UsuarioRESTClient usuRest = new UsuarioRESTClient();
    	Usuario usuario = usuRest.findByLogin(u.getLogin());
    	usuario.setPerfil();
		pedido.setUsuario(usuario);
		//seta a data
		Date data = new Date();
		pedido.setData(data);
		//pega o servico e seta ele
		List<Servico> servicos = new ArrayList();
		servicos.add(servico);
		pedido.setServicos(servicos);
		//seta a situacao
		pedido.setSituacaos(new ArrayList());
		//cria o pedido
    	PedidoRESTClient rest = new PedidoRESTClient();
    	Pedido novo = rest.create(pedido);
    	if (novo != null) {
    		return "/protected/requisicoes?faces-redirect=true";	
    	}
    	else {
    		return null;
    	}
	}

}
