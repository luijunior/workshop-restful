package br.com.rest.service.modelo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Erro {
	
	private int status;
	private String mensagem;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
