package br.com.rest.service.modelo;

import javax.ws.rs.core.Response;



public class ClienteInexistente extends RestException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5266555487445584115L;
	private Erro erro;
	
	public ClienteInexistente(String msg,Exception e,int status) {
		super(e);
		erro = new Erro();
		erro.setMensagem(msg);
		erro.setStatus(status);
	}

	public Erro getErro() {
		return erro;
	}

	public void setErro(Erro erro) {
		this.erro = erro;
	}

	@Override
	public Response responde() {
		// TODO Auto-generated method stub
		return Response.status(erro.getStatus()).entity(erro).build();
	}

}
