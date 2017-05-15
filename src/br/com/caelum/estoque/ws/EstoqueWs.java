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
import br.com.caelum.estoque.modelo.item.ItemValidador;
import br.com.caelum.estoque.modelo.item.ListaItens;
import br.com.caelum.estoque.modelo.usuario.AutorizacaoException;
import br.com.caelum.estoque.modelo.usuario.TokenDao;
import br.com.caelum.estoque.modelo.usuario.TokenUsuario;

@WebService
public class EstoqueWs {
	
	ItemDao dao = new ItemDao();
	
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
	public Item cadastrarItem(@WebParam(name="tokenUsuario", header=true) TokenUsuario token, @WebParam(name="item")Item item) throws AutorizacaoException {
		System.out.println("Usuário: "+token +"\n"+"Cadastrando Item:" + item);
		
		boolean tokenValido = new TokenDao().ehValido(token);
		
		if(!tokenValido) {
			throw new AutorizacaoException("Autorização Falhou"); 
		}
		
		new ItemValidador(item).validate();
		
		this.dao.cadastrar(item);
		return item;
	}

}
