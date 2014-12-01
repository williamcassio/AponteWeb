package service;

import java.util.List;
import entity.Apontamento;
import entity.Itens;

public interface ItensService {
	public int salvar(Itens item);
	public int atualizar(Itens item);
	public void excluir(Itens item);
	public Itens carregar(Integer id);
	public List<Itens> listar(Apontamento aponte);// Existe uma diferença pois este lista por aponamento
}
