package br.com.rest.dao;

import br.com.rest.service.modelo.Cliente;
import br.com.rest.service.modelo.ClienteInexistente;

public interface ClienteDao {
	
	Cliente buscaPorCpf(String cpf) throws ClienteInexistente;

	Cliente buscaUltimoAlteradoPorCpf(String cpf) throws ClienteInexistente;

	void atualizaCliente(Cliente cliente) throws ClienteInexistente;

}
