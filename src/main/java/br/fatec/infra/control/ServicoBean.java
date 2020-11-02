package br.fatec.infra.control;


import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import br.fatec.infra.model.Servico;
import java.util.List;

@ManagedBean
@RequestScoped
public class ServicoBean implements Serializable{
    private static final long serialVersionUID = 1L;
    private Servico servico;
    private List<Servico> servicos;
    
    public ServicoBean() {
    	
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

}
