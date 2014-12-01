package service;

import java.util.List;

import entity.Usuario;

public interface UsuarioService {
	public int salvar(Usuario usuario);
	public int atualizar(Usuario usuario);
	public void excluir(Usuario usuario);
	public Usuario carregar(Integer id);
	public Usuario buscarPorLogin(String login);
	public List<Usuario> listar();
	public boolean login(String login, String senha);
	public List<Usuario> listarRecebeEmail();
}
