package bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import rn.UsuarioRN;
import utils.MensagensUtil;
import entity.Usuario;

@SuppressWarnings("serial")
@ManagedBean(name="usuarioBean")
@ViewScoped
public class UsuarioBean  implements Serializable{
	private Usuario usuario;
	private String confirmaSenha;
	private List<Usuario> lista;
	
	private boolean pnEdicao = false;
	
	private String labelOperacao = "Novo Usuário";
	
	private boolean novoLoginSalvo = false;
	
	public boolean isNovoLoginSalvo() {
		return novoLoginSalvo;
	}

	public String getLabelOperacao() {
		return labelOperacao;
	}

	public UsuarioBean(){
		this.usuario = new Usuario();
		this.usuario.setAtivo(true);
		this.usuario.setAdministrador(false);
		this.usuario.setRecebeemail(false);
	}
	
	public List<Usuario> getLista() {
		if (this.lista == null){
			UsuarioRN usuarioRN = new UsuarioRN();
			this.lista = usuarioRN.listar();
		}
		return this.lista;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getConfirmaSenha() {
		return confirmaSenha;
	}
	
	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
	
	public String novo(){
		this.usuario = new Usuario();
		this.labelOperacao = "Novo Usuário";
		this.confirmaSenha = "";
		return "usuario";
	}
	
	public String novodeUsuarios(){
		this.usuario = new Usuario();
		this.usuario.setAtivo(true);
		this.pnEdicao = true;
		this.labelOperacao = "Novo Usuário";
		this.confirmaSenha = "";
		return null;
	}	
	
	public String salvardeUsuario(){
		String senha = this.usuario.getSenha();
		if (!senha.equals(this.confirmaSenha)){
			MensagensUtil.mensagemErro("A senha não foi confirmada correntamente");
			return null;
		}
		UsuarioRN usuarioRN = new UsuarioRN();
		if (usuarioRN.salvar(this.usuario) == 1){
			this.pnEdicao = false;
			this.lista = null;
			return null;
		} else{
			MensagensUtil.mensagemInfo("Erro na gravação do registro!");
			return null;
		}		
	}
	
	public String salvar(){
		String senha = this.usuario.getSenha();
		if (!senha.equals(this.confirmaSenha)){
			MensagensUtil.mensagemErro("A senha não foi confirmada correntamente");
			return null;
		}
		UsuarioRN usuarioRN = new UsuarioRN();
		if (usuarioRN.salvar(this.usuario) == 1){
			this.novoLoginSalvo = true;
			return null;
		} else{
			MensagensUtil.mensagemInfo("Erro na gravação do registro!");
			return null;
		}
	}
	
	public String editar(){
		this.confirmaSenha = this.usuario.getSenha();
		this.pnEdicao = true;
		this.labelOperacao = "Edição de Usuário";
		return "/publico/usuario?faces-redirect=true";
	}
	
	public String excluir(){
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.excluir(this.usuario);
		this.lista = null;
		return null;
	}
	
	public String excluirnaEdicao(){
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.excluir(this.usuario);
		this.lista = null;		
		return null;
	}
	
	public String doPrincipal(){
		return "/privado/principal";
	}
	
	public String doCancelar(){
		this.pnEdicao = false;
		return null;
	}

	public boolean isPnEdicao() {
		return pnEdicao;
	}

	public void setPnEdicao(boolean pnEdicao) {
		this.pnEdicao = pnEdicao;
	}
		
}
