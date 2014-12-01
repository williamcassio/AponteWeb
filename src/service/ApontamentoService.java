package service;

import java.util.Date;
import java.util.List;

import entity.Apontamento;
import entity.Usuario;

public interface ApontamentoService {
	public Apontamento salvar(Apontamento aponte);
	public Apontamento atualizar(Apontamento aponte);
	public void excluir(Apontamento aponte);
	public Apontamento carregar(Integer id);
	public List<Apontamento> listar(Usuario usuario); // Aqui tamb�m a listagem � diferente, buscar por usu�rio
	public List<Apontamento> listarPorData(Usuario usuario, Date di, Date df); // Aqui tamb�m a listagem � diferente, buscar por usu�rio
	public List<Apontamento> listarGeral();

}
