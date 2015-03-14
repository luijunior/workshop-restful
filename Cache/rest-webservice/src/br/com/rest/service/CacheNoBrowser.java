package br.com.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;

import br.com.rest.service.modelo.Cliente;

@Path("/noBrowser")
public class CacheNoBrowser {
	//Efetua cache no browser em diferentes janelas (Exceto F5 que forca recarregar cache)

	@GET
	@Path("/private")
	public Response retornaClientePrivado(){
		System.out.println("Chamada no servidor [retornaClientePrivado]");

		CacheControl cc = new CacheControl();
		cc.setMaxAge(150);
		cc.setPrivate(true);
		return Response.status(200).cacheControl(cc).entity(geraCliente("privado")).build();
		
	}
	
	@GET
	@Path("/public")
	public Response retornaClientePublico(){
		System.out.println("Chamada no servidor [retornaClientePublico]");
		
		CacheControl cc = new CacheControl();
		cc.setMaxAge(150);
		cc.setPrivate(false);
		return Response.status(200).cacheControl(cc).entity(geraCliente("Publico")).build();
		
	}
	
	private Cliente geraCliente(String nome){
		Cliente cliente = new Cliente();
		cliente.setCpf("123455678901");
		cliente.setNome(nome);
		return cliente;
	}

}
