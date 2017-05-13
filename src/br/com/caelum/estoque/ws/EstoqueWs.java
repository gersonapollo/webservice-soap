package br.com.caelum.estoque.ws;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.com.caelum.estoque.modelo.item.Item;
import br.com.caelum.estoque.modelo.item.ItemDao;
import br.com.caelum.estoque.modelo.item.ListaItens;

@WebService
public class EstoqueWs {
	
	ItemDao dao = new ItemDao();
	
	@WebMethod(operationName="todosOsItens")
	@WebResult(name="Itens")
	public ListaItens getItens(){
		System.out.println("Executando Chamada getItens");
		ArrayList<Item> itens = dao.todosItens();
		return new ListaItens(itens);
	}

}
