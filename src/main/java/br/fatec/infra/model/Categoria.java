package br.fatec.infra.model;

import java.io.Serializable;
import java.util.List;

public class Categoria implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private List<Servico> servicos;
	
	public Categoria() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
}
