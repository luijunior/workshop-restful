package br.com.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.rest.dao.ClienteDao;
import br.com.rest.service.modelo.ClienteInexistente;
import br.com.rest.service.modelo.RestException;

@Path("/cliente")
public class CacheNoBrowser {
	//Efetua cache no browser em diferentes janelas (Exceto F5 que forca recarregar cache)
	private final ClienteDao cliDao;
	
	@Autowired
	public CacheNoBrowser(ClienteDao cliDao) {
		this.cliDao = cliDao;
	}
	
	@GET
	@Path("/{cpf}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response buscaCliente(@PathParam("cpf") String cpf,@QueryParam("public") boolean publico){
		if(publico){
			return retornaClientePublico(cpf);
		}
		else{
			return retornaClientePrivado(cpf);
		}
	}
	
	
	
	public Response retornaClientePrivado(String cpf){
		System.out.println("Chamada no servidor [retornaClientePrivado]");
		try {
			CacheControl cc = configuraCache(true);
			return Response.status(200).cacheControl(cc).entity(this.cliDao.buscaPorCpf(cpf)).build();
		} catch (RestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.responde();
		}
		
	}
	
	public Response retornaClientePublico(String cpf){
		System.out.println("Chamada no servidor [retornaClientePublico]");
		try {
			CacheControl cc = configuraCache(false);
			return Response.status(200).cacheControl(cc).entity(this.cliDao.buscaPorCpf(cpf)).build();
		} catch (ClienteInexistente e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.responde();
		}
		
	}
	
	private CacheControl configuraCache(boolean publica){
		CacheControl cc = new CacheControl();
		cc.setMaxAge(150);
		cc.setPrivate(publica);
		return cc;
	}

}
