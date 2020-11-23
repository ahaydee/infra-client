package br.fatec.infra.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pedido implements Serializable{
    private static final long serialVersionUID = 1L;
	private Long id;
	private Servico servico;
	private Date data;	
	private Usuario usuario;
	private List<String> situacaos = new ArrayList();

	public Pedido() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<String> getSituacaos() {
		return situacaos;
	}

	public void setSituacaos(List<String> situacaos) {
		this.situacaos = situacaos;
	}
}
