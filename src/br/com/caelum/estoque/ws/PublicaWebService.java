package br.com.caelum.estoque.ws;

import javax.xml.ws.Endpoint;

public class PublicaWebService {

	public static void main(String[] args) {

		EstoqueWs servico = new EstoqueWs();
		String url = "http://localhost:8080/estoquews";
		
		System.out.println("Servico Publicado: "+ url +"?wsdl");
		Endpoint.publish(url, servico);
		
		
	}

}
