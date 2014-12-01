package rn;

import java.util.Date;
import java.util.List;

import service.ApontamentoService;
import dao.ApontamentoDAO;
import entity.Apontamento;
import entity.Usuario;

public class ApontamentoRN {
	
	private ApontamentoService apontamentoService;
	
	public ApontamentoRN(){
		apontamentoService = ApontamentoDAO.getInstance();
	}
	
	public Apontamento salvar(Apontamento aponte){
		Integer id = aponte.getId();
		if(id == null || id == 0){
			return apontamentoService.salvar(aponte);
		} else{
			return apontamentoService.atualizar(aponte);
		}
	}
	
	public void excluir(Apontamento aponte){
		apontamentoService.excluir(aponte);
	}
	
	public Apontamento carregar(Integer id){
		return apontamentoService.carregar(id);
	}
	
	public List<Apontamento> listar(Usuario usuario){
		return apontamentoService.listar(usuario);
	}
	
	public List<Apontamento> listarPorData(Usuario usuario, Date di, Date df){
		return apontamentoService.listarPorData(usuario, di, df);
	}
	
	public List<Apontamento> listarGeral(){
		return apontamentoService.listarGeral();
	}
	
}
