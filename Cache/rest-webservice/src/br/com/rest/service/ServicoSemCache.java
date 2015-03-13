package br.com.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.rest.dao.ClienteDao;
import br.com.rest.service.modelo.Cliente;
import br.com.rest.service.modelo.ClienteInexistente;

@Path("/semCache")
public class ServicoSemCache {
	
	private ClienteDao clienteDao;
	
	@Autowired
	public ServicoSemCache(ClienteDao clienteDao) {
		this.clienteDao = clienteDao;
	}
	
	
	@GET
	@Path("/cliente/{cpf}")
	@Produces(value={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response buscaCliente(@PathParam("cpf")String cpf){
		
		Cliente cliente;
		try {
			System.out.println("Chamando servico no servidor sem cache");
			cliente = this.clienteDao.buscaPorCpf(cpf);
			return Response.status(200).entity(cliente).build();
		} catch (ClienteInexistente e) {
			// TODO Auto-generated catch block
			return Response.status(e.getErro().getStatus()).entity(e.getErro()).build();
		}
		

		
	}

}
