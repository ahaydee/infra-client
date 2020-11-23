package br.fatec.infra.model;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private String login;
	private String senha;
	private Set<String> perfis = new HashSet<String>();
	private String departamento;
	private List<Pedido> pedidos;
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Usuario() {
	}

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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<TipoPerfil> getPerfis() {
		return perfis.stream()
				.map(x -> TipoPerfil.valueOf(x))
				.collect(Collectors.toSet());
	}

	public void setPerfil() {
		perfis = new HashSet<String>();
	}
	
	public void addPerfil(TipoPerfil perfil) {
		perfis.add(perfil.getDescricao());
	}
	

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
}
