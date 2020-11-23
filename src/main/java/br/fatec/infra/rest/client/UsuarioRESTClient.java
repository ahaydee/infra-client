package br.fatec.infra.rest.client;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.fatec.infra.dto.CredenciaisDTO;
import br.fatec.infra.model.Categoria;
import br.fatec.infra.model.Usuario;
import br.fatec.infra.util.SessionContext;

public class UsuarioRESTClient implements RESTClientInterface<Usuario> {

	private Response response;
	private String token = (String) SessionContext.getInstance().getAttribute("token");

	@Override
	public List<Usuario> findAll() {
		this.response = ClientBuilder.newClient()
                .target(REST_WEBSERVICE_URL + REST_USUARIO_URL)
                .request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.get();

		List<Usuario> usuarios = this.response
		.readEntity(new GenericType<List<Usuario>>() {});
		return usuarios;
	}

	@Override
	public Usuario find(Long id) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_USUARIO_URL + id)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.get();
		if (response.getStatus() == STATUS_OK) {
			Usuario usuario = this.response.readEntity(Usuario.class);
			return usuario;
		}
		return null;
	}

	public Usuario findByLogin(String login) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_USUARIO_URL + "login/"+ login)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.get();
		if (response.getStatus() == STATUS_OK) {
			Usuario usuario = this.response.readEntity(Usuario.class);
			return usuario;
		}
		return null;
	}


	@Override
	public Usuario create(Usuario obj) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_USUARIO_URL)
				.queryParam("usuario", obj)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.post(Entity.entity(obj, MediaType.APPLICATION_JSON));
		Usuario usuario = this.response.readEntity(Usuario.class);

		return usuario;
	}

	@Override
	public Usuario edit(Usuario obj) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_USUARIO_URL)
				.queryParam("usuario", obj)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.put(Entity.entity(obj, MediaType.APPLICATION_JSON));
		if (this.response.getStatus() == STATUS_OK) {
			Usuario usuario = this.response.readEntity(Usuario.class);
			return usuario;
		}
		return null;
	}

	@Override
	public boolean delete(Long id) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_USUARIO_URL + id)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.delete();
		return this.response.getStatus() == STATUS_OK;
	}
	
	public boolean authenticate(CredenciaisDTO usuario) {		
		this.response = ClientBuilder.newClient().
				target("http://localhost:8080/login").
	    		queryParam("usuario", usuario).
	    		request(MediaType.APPLICATION_JSON).
	    		post(Entity.entity(usuario, MediaType.APPLICATION_JSON));
		if (response.getStatus() == Response.Status.OK.getStatusCode()) {
			String _admin = (response.getHeaderString("x-admin"));
			usuario.setAdmin(_admin.equals("yes"));
			SessionContext.getInstance().setAttribute("usuario", usuario);
			String token = response.getHeaderString("Authentication");
			SessionContext.getInstance().setAttribute("token", token);
			return true;
		}	    
		return false;
	}
}
