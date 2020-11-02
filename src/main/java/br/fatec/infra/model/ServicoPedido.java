package br.fatec.infra.model;

import java.io.Serializable;

public class ServicoPedido implements Serializable{
    private static final long serialVersionUID = 1L;
    private Servico servico;
    private String descricao;
    
    public ServicoPedido() {}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
    
}
