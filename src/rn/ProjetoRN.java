package rn;

import java.util.List;

import service.ProjetoService;
import dao.ProjetoDAO;
import entity.Projeto;

public class ProjetoRN {

	private ProjetoService projetoService;
	
	public ProjetoRN(){
		projetoService = ProjetoDAO.getInstance();
	}
	
	public int salvar(Projeto projeto){
		Integer id = projeto.getId();
		if (id == null || id == 0){
			return projetoService.salvar(projeto);
		} else{
			return projetoService.atualiza(projeto);
		}
	}
	
	public void excluir(Projeto projeto){
		projetoService.excluir(projeto);
	}
	
	public Projeto carregar(Integer id){
		return projetoService.carregar(id);
	}
	
	public List<Projeto> listar(){
		return projetoService.listar();
	}
}
