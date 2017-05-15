package br.com.caelum.estoque.modelo.usuario;

import javax.xml.ws.WebFault;

@WebFault(name="AutorizacaoFault", messageName="AutorizacaoFault")
public class AutorizacaoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AutorizacaoException(String msg) {
		super(msg);
	}
	
	public String getFaultInfo() {
		return "Token Invalido";
	}

}
