package br.com.rest.service.modelo;



public class ClienteInexistente extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5266555487445584115L;
	private Erro erro;
	
	public ClienteInexistente(String msg,Exception e,int status) {
		super(msg,e);
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

}
