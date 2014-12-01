package service;

import java.util.List;

import entity.Tipo;

public interface TipoService {
	public int salvar(Tipo tipo);
	public int atualiza(Tipo tipo);
	public void excluir(Tipo tipo);
	public Tipo carregar(Integer id);
	public List<Tipo> listar();
}
