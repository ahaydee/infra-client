package br.fatec.infra.rest.client;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.fatec.infra.model.Servico;
import br.fatec.infra.util.SessionContext;

public class ServicoRESTClient implements RESTClientInterface<Servico> {
	private Response response;
	private String token = (String) SessionContext.getInstance().getAttribute("token");

	@Override
	public List<Servico> findAll() {
		this.response = ClientBuilder.newClient()
                .target(REST_WEBSERVICE_URL + REST_SERVICO_URL)
                .request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.get();

		List<Servico> servicos = this.response
				.readEntity(new GenericType<List<Servico>>() {});
		return servicos;
	}

	@Override
	public Servico find(Long id) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_SERVICO_URL + id)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.get();
		if (response.getStatus() == STATUS_OK) {
			Servico servico = this.response.readEntity(Servico.class);
			return servico;
		}
		return null;
	}
	
	public List<Servico> findByCategoria(Long categoria) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_SERVICO_URL + "categoria/" + categoria)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.get();

		List<Servico> servicos = this.response
				.readEntity(new GenericType<List<Servico>>() {});
		return servicos;
	}


	@Override
	public Servico create(Servico obj) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_SERVICO_URL)
				.queryParam("servico", obj)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.post(Entity.entity(obj, MediaType.APPLICATION_JSON));
		Servico servico = this.response.readEntity(Servico.class);

		return servico;
	}

	@Override
	public Servico edit(Servico obj) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_SERVICO_URL)
				.queryParam("servico", obj)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.put(Entity.entity(obj, MediaType.APPLICATION_JSON));
		if (this.response.getStatus() == STATUS_OK) {
			Servico servico = this.response.readEntity(Servico.class);
			return servico;
		}
		return null;
	}

	@Override
	public boolean delete(Long id) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_SERVICO_URL + id)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.delete();
		return this.response.getStatus() == STATUS_OK;
	}

}
