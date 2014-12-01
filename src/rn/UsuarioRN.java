package rn;

import java.util.List;
import service.UsuarioService;
import dao.UsuarioDAO;
import entity.Usuario;

public class UsuarioRN {
	private UsuarioService usuarioService;
	
	public UsuarioRN(){
		this.usuarioService = UsuarioDAO.getInstance(); 
	}
	
	public Usuario carregar(Integer id){
		return this.usuarioService.carregar(id);
	}
	
	public Usuario buscarPorLogin(String login){
		return this.usuarioService.buscarPorLogin(login);
	}
	
	public int salvar(Usuario usuario){
		Integer id = usuario.getId();
		if (id == null || id == 0){
			id = this.usuarioService.salvar(usuario);
			return id;
		} else {
			return this.usuarioService.atualizar(usuario);
		}
	}
	
	public void excluir(Usuario usuario){
		this.usuarioService.excluir(usuario);
	}
	
	public List<Usuario> listar(){
		return this.usuarioService.listar();
	}
	
	public boolean login(String login, String senha){
		return this.usuarioService.login(login, senha);
	}
	
	public List<Usuario> listarRecebeEmail(){
		return this.usuarioService.listarRecebeEmail();
	}
	
}
