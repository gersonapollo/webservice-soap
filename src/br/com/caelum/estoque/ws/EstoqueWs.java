package br.com.caelum.estoque.ws;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import br.com.caelum.estoque.modelo.item.Filtro;
import br.com.caelum.estoque.modelo.item.Filtros;
import br.com.caelum.estoque.modelo.item.Item;
import br.com.caelum.estoque.modelo.item.ItemDao;
import br.com.caelum.estoque.modelo.item.ListaItens;

@WebService
public class EstoqueWs {
	
	ItemDao dao = new ItemDao();
	
//	@WebMethod(operationName="todosOsItens")
//	@WebResult(name="Itens")
//	public ListaItens getItens(){
//		System.out.println("Executando Chamada getItens");
//		ArrayList<Item> itens = dao.todosItens();
//		return new ListaItens(itens);
//	}
	
	@WebMethod(operationName="ItensPorFiltro")
	@WebResult(name="Itens")
	@RequestWrapper(localName="ItensParaConsulta")
	@ResponseWrapper(localName="ListaItens")
	public ListaItens getItens(@WebParam(name="filtros") Filtros filtros){
		System.out.println("Executando Chamada getItens com filtros");
		List<Filtro> lista = filtros.getLista();
		ArrayList<Item> itens = dao.todosItens(lista);
		return new ListaItens(itens);
	}
	
	@WebMethod(operationName="CadastrarItem")
	@WebResult(name="Item")
	public Item cadastrarItem(@WebParam(name="item")Item item) {
		System.out.println("Cadastrando Item:" + item);
		this.dao.cadastrar(item);
		return item;
	}

}
