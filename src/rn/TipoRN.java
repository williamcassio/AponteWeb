package rn;

import java.util.List;

import service.TipoService;
import dao.TipoDAO;
import entity.Tipo;

public class TipoRN {
	private TipoService tipoService;
	
	public TipoRN(){
		tipoService = TipoDAO.getInstance();
	}
	
	public int salvar(Tipo tipo){
		Integer id = tipo.getId();
		if (id == null || id==0){
			return tipoService.salvar(tipo);
		} else{
			return tipoService.atualiza(tipo);
		}
	}
	
	public void excluir(Tipo tipo){
		tipoService.excluir(tipo);
	}
	
	public Tipo carregar(Integer id){
		return tipoService.carregar(id);
	}
	
	public List<Tipo> listar(){
		return tipoService.listar();
	}
}
