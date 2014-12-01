package service;

import java.util.List;

import entity.Projeto;

public interface ProjetoService {
	public int salvar(Projeto projeto);
	public int atualiza(Projeto projeto);
	public void excluir(Projeto projeto);
	public Projeto carregar(Integer id);
	public List<Projeto> listar();

}
