package bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import rn.UsuarioRN;
import utils.MensagensUtil;
import entity.Usuario;

@SuppressWarnings("serial")
@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean implements Serializable {
	
	private String login;
	private String senha;
	
	private Usuario usuarioLogado = new Usuario();
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String efetuarLogin(){
		UsuarioRN usuarioRN = new UsuarioRN();
		Usuario user = usuarioRN.buscarPorLogin(login);
		if (user == null){
			MensagensUtil.mensagemErro("Usuário ou senha inválida");
			this.login = null;
			this.senha = null;
			return null;
		} else if (!user.getSenha().equals(senha)){
			MensagensUtil.mensagemErro("Usuário ou senha inválida");
			this.login = null;
			this.senha = null;
			return null;
		} else if (!user.isAtivo()) {
			MensagensUtil.mensagemErro("Usuário Inativo. Entre em contato com o Administrador do Sistema");
			this.login = null;
			this.senha = null;
			return null;			
		} else{
			usuarioLogado = user;
			return "/privado/principal?faces-redirect=true";
		}
	}
	
	public String efetuarLogout(){
		this.usuarioLogado = null;
		login = null;
		senha = null;
		return "/login?faces-redirect=true";		
	}	
}
