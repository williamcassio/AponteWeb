package bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import rn.UsuarioRN;
import utils.ContextoUtil;
import utils.MensagensUtil;
import entity.Usuario;

@SuppressWarnings("serial")
@ManagedBean(name="principalBean")
@RequestScoped
public class PrincipalBean implements Serializable {
	
	private String senAtual;
	private String senNova;
	private String senConf;
	private boolean administrador = false;

	public boolean isAdministrador() {
		return administrador;
	}

	public String getSenAtual() {
		return senAtual;
	}

	public void setSenAtual(String senAtual) {
		this.senAtual = senAtual;
	}

	public String getSenNova() {
		return senNova;
	}

	public void setSenNova(String senNova) {
		this.senNova = senNova;
	}

	public String getSenConf() {
		return senConf;
	}

	public void setSenConf(String senConf) {
		this.senConf = senConf;
	}

	public String usuarios(){
		return "/privado/usuarios?faces-redirect=true";
	}
	
	public String tipos(){
		return "/privado/tipos?faces-redirect=true";
	}
	
	public String projetos(){
		return "/privado/projetos?faces-redirect=true";
	}
	
	public String apontamentos(){
		return "/privado/apontes?faces-redirect=true";
	}
	
	public PrincipalBean(){
		if (ContextoUtil.getContextoBean() != null){
			administrador = ContextoUtil.getContextoBean().getUsuarioLogado().isAdministrador();
		}
	}
	
	public void confereSenha(){
		boolean bRetorno;
		Usuario user = new Usuario();
		UsuarioRN usuarioRN = new UsuarioRN();
		bRetorno = usuarioRN.login(ContextoUtil.getContextoBean().getUsuarioLogado().getLogin(), senAtual);
		if (bRetorno == false){
			MensagensUtil.mensagemErro("Senha atual não confere");
		} else if (!senNova.equals(senConf)) {
			MensagensUtil.mensagemErro("Senha nova não confere!");
		} else{
			user = usuarioRN.buscarPorLogin(ContextoUtil.getContextoBean().getUsuarioLogado().getLogin());
			user.setSenha(senNova);
			usuarioRN.salvar(user);
			MensagensUtil.mensagemInfo("Senha alterada com sucesso!");
		}
	}	
	
	


}
