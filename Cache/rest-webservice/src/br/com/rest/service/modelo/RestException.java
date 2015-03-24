package br.com.rest.service.modelo;

import javax.ws.rs.core.Response;

public abstract class RestException extends Exception{
	
	private static final long serialVersionUID = -1360057168733803661L;

	public RestException(Exception e){
		super(e);
	}
	
	public abstract Response responde();
}
