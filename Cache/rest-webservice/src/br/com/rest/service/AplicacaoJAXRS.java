package br.com.rest.service;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;



@ApplicationPath("resources")
public class AplicacaoJAXRS extends ResourceConfig{
	
	
	public AplicacaoJAXRS(){
		super(JacksonFeature.class);
		register(RequestContextFilter.class);
		register(ServicoSemCache.class);
		register(ServicoComCache.class);
		register(AlteraCliente.class);

	}
	

}