package br.fatec.infra.model;

import java.io.Serializable;

public class Servico implements Serializable{
    private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private String descricao;
	private Categoria categoria;
	
	public Servico() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
