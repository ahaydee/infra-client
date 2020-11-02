package br.fatec.infra.model;

import java.io.Serializable;

public class SituacaoPedido implements Serializable{
    private static final long serialVersionUID = 1L;
    private Situacao situacao;
    private String descricao;
    private String data;
    
    public SituacaoPedido() {}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
    
}
