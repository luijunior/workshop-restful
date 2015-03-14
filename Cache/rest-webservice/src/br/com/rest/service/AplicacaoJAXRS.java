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
		register(CacheNoBrowser.class);
		register(CacheNoServer.class);
		register(SemCache.class);
		register(AlteraCliente.class);

	}
	

}