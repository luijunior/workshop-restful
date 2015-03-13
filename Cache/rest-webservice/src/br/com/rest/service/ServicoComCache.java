package br.com.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.rest.dao.ClienteDao;
import br.com.rest.service.modelo.Cliente;
import br.com.rest.service.modelo.ClienteInexistente;

@Path("/comCache")
public class ServicoComCache {
	
private ClienteDao clienteDao;
	
	@Autowired
	public ServicoComCache(ClienteDao clienteDao) {
		this.clienteDao = clienteDao;
	}
	
	
	@GET
	@Path("/cliente/{cpf}")
	@Produces(value={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response buscaCliente(@PathParam("cpf")String cpf , @Context Request  request){
		
		Cliente cliente;
		try {
			System.out.println("Chamando servico no servidor com cache");
			cliente = this.clienteDao.buscaUltimoAlteradoPorCpf(cpf);
			EntityTag etag = configuraTag(cliente);
			System.out.println(etag.getValue());
			ResponseBuilder builder = request.evaluatePreconditions(etag);
			if(builder!=null){
				System.out.println("Com cache");
				builder.cacheControl(configuraCache());
		       return builder.build();

			}
			System.out.println("Sem cache");
			cliente = this.clienteDao.buscaPorCpf(cpf);
			builder = Response.ok().entity(cliente).tag(etag).cacheControl(configuraCache());
			return builder.build();
		} catch (ClienteInexistente e) {
			// TODO Auto-generated catch block
			return Response.status(e.getErro().getStatus()).entity(e.getErro()).build();
		}
	}
	
	private CacheControl configuraCache(){
		
		CacheControl cc = new CacheControl();
        cc.setMaxAge(50000);
        return cc;
	}
	
	private EntityTag configuraTag(Cliente cliente){
		EntityTag tag = new EntityTag(Integer.toString(cliente.hashCode()));
		return tag;
	}

}
