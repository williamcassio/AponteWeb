package rn;

import java.util.Iterator;
import java.util.List;

import service.ItensService;
import dao.ItensDAO;
import entity.Apontamento;
import entity.Itens;

public class ItensRN {
	private ItensService itensService;
	
	public ItensRN(){
		itensService = ItensDAO.getInstance();
	}
	
	public int salvar(Itens item){
		Integer id = item.getId();
		if (id == null || id == 0){
			return itensService.salvar(item);
		} else{
			return itensService.atualizar(item);
		}
	}
	
	public void excluir(Itens item){
		itensService.excluir(item);
	}
	
	public Itens carregar(Integer id){
		return itensService.carregar(id);
	}
	
	public List<Itens> listar(Apontamento aponte){
		return itensService.listar(aponte);
	}
	
	@SuppressWarnings("rawtypes")
	public void excluirTodosApontamento(List<Itens> lista){
		for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
			Itens itens = (Itens) iterator.next();
			itensService.excluir(itens);
		}
	}
	
}
