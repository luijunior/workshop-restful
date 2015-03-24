package br.com.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.rest.dao.ClienteDao;
import br.com.rest.service.modelo.Cliente;
import br.com.rest.service.modelo.ClienteInexistente;

@Path("/cliente")
public class AlteraCliente {
	
private ClienteDao clienteDao;
	
	@Autowired
	public AlteraCliente(ClienteDao clienteDao) {
		this.clienteDao = clienteDao;
	}
	
	@PUT
	@Path("/{cpf}")
	@Consumes(value={MediaType.APPLICATION_JSON})
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response atualizaExistente(@PathParam("cpf") String cpf , Cliente cliente){
		
		cliente.setCpf(cpf);
		try {
			this.clienteDao.atualizaCliente(cliente);
			return Response.status(204).build();
		} catch (ClienteInexistente e) {
			// TODO Auto-generated catch block
			return Response.status(e.getErro().getStatus()).entity(e.getErro()).build();
		}
		
		
	}

}
