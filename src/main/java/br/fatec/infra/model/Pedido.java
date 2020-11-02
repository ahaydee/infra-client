package br.fatec.infra.model;

import java.io.Serializable;
import java.util.List;

public class Pedido implements Serializable{
    private static final long serialVersionUID = 1L;
	private Long id;
	private List<ServicoPedido> servicos;
	private String data;
	private Usuario usuario;
	private List<SituacaoPedido> situacaoAtual;
	private List<SituacaoPedido> situacao;
	
	public List<SituacaoPedido> getSituacaoAtual() {
		return situacaoAtual;
	}

	public void setSituacaoAtual(List<SituacaoPedido> situacaoAtual) {
		this.situacaoAtual = situacaoAtual;
	}

	public Pedido() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public List<ServicoPedido> getServicos() {
		return servicos;
	}
	public void setServicos(List<ServicoPedido> servicos) {
		this.servicos = servicos;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<SituacaoPedido> getSituacao() {
		return situacao;
	}
	public void setSituacao(List<SituacaoPedido> situacao) {
		this.situacao = situacao;
	}
}
