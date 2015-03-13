package br.com.rest.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.rest.service.modelo.Cliente;
import br.com.rest.service.modelo.ClienteInexistente;
@Repository
public class ClienteMemoria implements ClienteDao{

	private static List<Cliente> clientes;

	static {
		clientes = new ArrayList<Cliente>();
		Cliente cliente = new Cliente();
		cliente.setCpf("12312312345");
		cliente.setNome("Luiz Antonio");
		clientes.add(cliente);
	}

	@Override
	public Cliente buscaPorCpf(String cpf) throws ClienteInexistente {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (Cliente cli : clientes){
			if(cli.getCpf().equals(cpf));
			return clientes.get(clientes.indexOf(cli));
		}

		throw new ClienteInexistente("Cliente inexistente",new Exception("Erro"), 404);

	}

	@Override
	public Cliente buscaUltimoAlteradoPorCpf(String cpf) throws ClienteInexistente{
		for (Cliente cli : clientes){
			if(cli.getCpf().equals(cpf));
			return clientes.get(clientes.indexOf(cli));
		}

		throw new ClienteInexistente("Cliente inexistente",new Exception("Erro"), 404);

	}
	
	@Override
	public void atualizaCliente(Cliente cliente) throws ClienteInexistente{
		Cliente cli = buscaUltimoAlteradoPorCpf(cliente.getCpf());
		cli.setNome(cliente.getNome());
	}

}
