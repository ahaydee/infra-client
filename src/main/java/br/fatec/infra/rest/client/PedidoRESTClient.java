package br.fatec.infra.rest.client;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.fatec.infra.model.Pedido;
import br.fatec.infra.model.Servico;
import br.fatec.infra.rest.client.RESTClientInterface;
import br.fatec.infra.util.SessionContext;

public class PedidoRESTClient implements RESTClientInterface<Pedido> {
	private Response response;
	private String token = (String) SessionContext.getInstance().getAttribute("token");

	@Override
	public List<Pedido> findAll() {
		this.response = ClientBuilder.newClient()
                .target(REST_WEBSERVICE_URL + REST_PEDIDO_URL)
                .request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.get();

		List<Pedido> pedidos = this.response
				.readEntity(new GenericType<List<Pedido>>() {});
		return pedidos;
	}

	@Override
	public Pedido find(Long id) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_PEDIDO_URL + id)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.get();
		if (response.getStatus() == STATUS_OK) {
			Pedido pedido = this.response.readEntity(Pedido.class);
			return pedido;
		}
		return null;
	}


	@Override
	public Pedido create(Pedido obj) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_PEDIDO_URL)
				.queryParam("pedido", obj)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.post(Entity.entity(obj, MediaType.APPLICATION_JSON));
		System.out.println(this.response);
		try {
			Pedido pedido =  this.response.readEntity(Pedido.class);
			return pedido;
		}
		catch(Exception e) {
			return null;
		}
	}
	
	public List<Pedido> findByUsuario(String usuario) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_PEDIDO_URL + "usuario/" + usuario)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.get();

		List<Pedido> pedidos = this.response
				.readEntity(new GenericType<List<Pedido>>() {});
		return pedidos;
	}

	@Override
	public Pedido edit(Pedido obj) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_PEDIDO_URL)
				.queryParam("pedido", obj)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.put(Entity.entity(obj, MediaType.APPLICATION_JSON));
		if (this.response.getStatus() == STATUS_OK) {
			Pedido pedido = this.response.readEntity(Pedido.class);
			return pedido;
		}
		return null;
	}
	public Pedido reprovar(Pedido obj) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_PEDIDO_URL + "reprovar/")
				.queryParam("pedido", obj)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.put(Entity.entity(obj, MediaType.APPLICATION_JSON));
		if (this.response.getStatus() == STATUS_OK) {
			Pedido pedido = this.response.readEntity(Pedido.class);
			return pedido;
		}
		return null;
	}
	public Pedido aprovar(Pedido obj) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_PEDIDO_URL + "aprovar/")
				.queryParam("pedido", obj)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.put(Entity.entity(obj, MediaType.APPLICATION_JSON));
		if (this.response.getStatus() == STATUS_OK) {
			Pedido pedido = this.response.readEntity(Pedido.class);
			return pedido;
		}
		return null;
	}

	@Override
	public boolean delete(Long id) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_PEDIDO_URL + id)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.delete();
		return this.response.getStatus() == STATUS_OK;
	}

}
