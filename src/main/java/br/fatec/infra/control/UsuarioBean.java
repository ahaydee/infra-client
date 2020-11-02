package br.fatec.infra.control;


import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import br.fatec.infra.model.Usuario;
import java.util.List;


@ManagedBean
@RequestScoped
public class UsuarioBean implements Serializable{
    private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private List<Usuario> usuarios;
	
	public UsuarioBean() {
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}
